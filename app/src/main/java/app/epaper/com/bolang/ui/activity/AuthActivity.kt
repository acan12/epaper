package app.epaper.com.bolang.ui.activity

import android.os.Bundle
import app.beelabs.com.codebase.base.BaseActivity
import app.beelabs.com.codebase.support.rx.RxTimer
import app.epaper.com.bolang.App
import app.epaper.com.bolang.R
import app.epaper.com.bolang.databinding.ActivitySplashBinding

class AuthActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
}