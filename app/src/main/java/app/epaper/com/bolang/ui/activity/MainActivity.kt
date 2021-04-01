package app.epaper.com.bolang.ui.activity

import android.Manifest
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import app.beelabs.com.codebase.base.BaseActivity
import app.beelabs.com.codebase.support.rx.RxTimer
import app.epaper.com.bolang.R
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


class MainActivity : BaseActivity(), PdfDownloader.StatusListener, OnPageChangeListener,
    OnLoadCompleteListener {
    private val sourceURL = "https://www.deusain.com/wp-content/uploads/2021/03/EPAPER_PB_514_24032021_OK-1.pdf"
    private lateinit var fileName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        RxTimer.doTimer(5000, false, object : RxTimer() {
            override fun onCallback(along: Long?) {

                Dexter.withContext(this@MainActivity)
                    .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ).withListener(object : MultiplePermissionsListener {
                        override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                            report?.let {
                                if (report.areAllPermissionsGranted()) {
                                    CoreUtil.buildPdfDownloader(getContext(), sourceURL, 123, this@MainActivity)
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
        pdf_view.fromUri(File(pdfPath).toUri())
            .defaultPage(0)
            .enableSwipe(true) // allows to block changing pages using swipe
            .enableDoubletap(true)
            .onLoad(this@MainActivity) // called after document is loaded and starts to be rendered
            .onPageChange(this@MainActivity)
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
        btn_save_offline.setOnClickListener { CoreUtil.savedOffline(fileName, this) }
    }

    override fun loadComplete(nbPages: Int) {
        progress_bar.visibility = View.GONE
    }

    override fun getContext(): Context = this

    override fun onDownloadStart() {
        Toast.makeText(this, "Download ... !", Toast.LENGTH_SHORT).show()
    }

    override fun onDownloadSuccess(absolutePath: String, fileName: String) {
        this.fileName = fileName
        renderPdfView(absolutePath)
        Toast.makeText(this, "Downloaded in $absolutePath !", Toast.LENGTH_LONG).show()
    }

}
