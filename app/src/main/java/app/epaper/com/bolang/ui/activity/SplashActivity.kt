package app.epaper.com.bolang.ui.activity

import android.os.Bundle
import app.beelabs.com.codebase.base.BaseActivity
import app.beelabs.com.codebase.support.rx.RxTimer
import app.epaper.com.bolang.App
import app.epaper.com.bolang.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RxTimer.doTimer(3000, false, object: RxTimer(){
            override fun onCallback(along: Long?) {
                App.getNavigationComponent()
                    .authNavigation()
                    .navigateToLogin(this@SplashActivity)
            }
        })
    }
}