package app.epaper.com.bolang.ui.activity

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import app.beelabs.com.codebase.base.BaseActivity
import app.beelabs.com.codebase.support.rx.RxTimer
import app.epaper.com.bolang.ui.tools.UiUtil
import app.epaper.com.bolang.databinding.ActivityMainBinding
import com.github.barteksc.pdfviewer.listener.OnFileDownloadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import java.io.File


class MainActivity : BaseActivity(), OnPageChangeListener, OnLoadCompleteListener,
    OnFileDownloadCompleteListener {
    private lateinit var binding: ActivityMainBinding
    private val PERMISSION_CODE: Int = 42042

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val permissionCheck = ContextCompat.checkSelfPermission(
            this,
            READ_EXTERNAL_STORAGE
        )

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf(READ_EXTERNAL_STORAGE),
                PERMISSION_CODE
            )
            return
        }

        RxTimer.doTimer(5000, false, object: RxTimer(){
            override fun onCallback(along: Long?) {
                binding.progressBar.visibility = View.GONE
                setupUI()
            }
        })

    }

    private fun setupUI() = with(binding) {
//        pdfView.fromAsset("pdf/epaper_demo.pdf")
//            .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
        pdfView.fromUrl("http://www.africau.edu/images/default/sample.pdf")
            .defaultPage(0)
            .enableSwipe(true) // allows to block changing pages using swipe
            .enableDoubletap(true)
            .onLoad(this@MainActivity) // called after document is loaded and starts to be rendered
            .onPageChange(this@MainActivity)
            .swipeHorizontal(false)
            .enableAntialiasing(false)
            .spacing(10)
            .onFileDownload(this@MainActivity)
            .load()

        RxTimer.doTimer(5000, false, object: RxTimer(){
            override fun onCallback(along: Long?) {
                pdfView.jumpTo(3, true)
            }
        })

    }

    override fun onPageChanged(page: Int, pageCount: Int) {
        Toast.makeText(this@MainActivity, "Page = $page", Toast.LENGTH_SHORT).show()
    }

    override fun loadComplete(nbPages: Int) {

    }

    override fun onDownloadComplete(file: File?) {

    }
}
