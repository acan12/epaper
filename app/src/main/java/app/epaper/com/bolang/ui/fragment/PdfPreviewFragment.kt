package app.epaper.com.bolang.ui.fragment

import android.Manifest
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import app.beelabs.com.codebase.base.BaseFragment
import app.beelabs.com.codebase.support.rx.RxTimer
import app.epaper.com.bolang.App
import app.epaper.com.bolang.IConfig
import app.epaper.com.bolang.R
import app.epaper.com.bolang.databinding.FragmentPdfPreviewBinding
import app.epaper.com.bolang.ui.tool.CoreUtil
import app.epaper.com.bolang.ui.tool.PdfDownloader
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class PdfPreviewFragment : BaseFragment(), PdfDownloader.StatusListener, OnPageChangeListener,
    OnLoadCompleteListener {

    private lateinit var binding: FragmentPdfPreviewBinding
    private lateinit var fileName: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPdfPreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var urlLilnk = arguments?.getString(IConfig.ARG_URL_PDF_LINK)
        var idLink = arguments?.getInt(IConfig.ARG_ID_PDF)
        var name = arguments?.getString(IConfig.ARG_NAME_PDF)
        setupUI(idLink, name, urlLilnk)
    }

    private fun setupUI(idLink: Int?, name: String?, pdfLink: String?) = with(binding) {
        RxTimer.doTimer(5000, false, object : RxTimer() {
            override fun onCallback(along: Long?) {

                Dexter.withContext(context)
                    .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ).withListener(object : MultiplePermissionsListener {
                        override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                            report?.let {
                                if (report.areAllPermissionsGranted()) {

                                    var fileName =
                                        context.resources.getString(R.string.pdf_filename_format)
                                            .replace(
                                                IConfig.TEXT_NAME_OF_PDF_REPLACEMENT,
                                                id.toString()
                                            )

                                    val fileSaved =
                                        CoreUtil.getFileSaved(fileName, requireContext())
                                    if (fileSaved.exists())
                                        renderPdfView(fileSaved.absolutePath)
                                    else
                                        PdfDownloader(pdfLink!!, fileName, this@PdfPreviewFragment)
                                    filenameLabel.text = name
                                    btnBack.setOnClickListener {
                                        App.getNavigationComponent().homeNavigation()
                                            .navigatePreviewToHome(binding.root, requireContext())
                                    }
                                }
                            }
                        }

                        override fun onPermissionRationaleShouldBeShown(
                            permissions: MutableList<PermissionRequest>?,
                            token: PermissionToken?
                        ) {
                            token?.continuePermissionRequest()
                        }
                    }).check()
            }
        })
    }

    private fun renderPdfView(pdfPath: String) {
        binding.pdfView.fromUri(File(pdfPath).toUri())
            .defaultPage(0)
            .enableSwipe(true) // allows to block changing pages using swipe
            .enableDoubletap(true)
            .onLoad(this) // called after document is loaded and starts to be rendered
            .onPageChange(this)
            .swipeHorizontal(isLandscape())
            .enableAntialiasing(true)
            .spacing(10)
            .load()
    }

    private fun isLandscape(): Boolean =
        resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE


    override fun onPageChanged(page: Int, pageCount: Int) {
        page_value.text = "${page + 1} / $pageCount"
        btn_prev.setOnClickListener { pdf_view.jumpTo(0) }
        btn_next.setOnClickListener { pdf_view.jumpTo(pageCount - 1) }
        btn_save_offline.setOnClickListener { CoreUtil.savedOffline(fileName, requireContext()) }
    }

    override fun loadComplete(nbPages: Int) = with(binding) {
        progressBar.visibility = View.GONE
        progressBarLabel.visibility = View.GONE
    }

    override fun getContext(): Context = activity as Context

    override fun onDownloadStart() {
        binding.progressBarLabel.text = "Downloading ... "
    }

    override fun onDownloadSuccess(absolutePath: String, fileName: String) {
        this.fileName = fileName
        renderPdfView(absolutePath)
    }


}