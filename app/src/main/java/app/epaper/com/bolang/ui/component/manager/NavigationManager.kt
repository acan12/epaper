package app.epaper.com.bolang.ui.component.manager

import android.content.Intent
import app.epaper.com.bolang.App
import app.epaper.com.bolang.ui.component.impl.IAuthNavigation
import app.epaper.com.bolang.ui.component.impl.IHomeNavigation
import app.epaper.com.bolang.ui.component.impl.INavigation

class NavigationManager : INavigation {
    override fun authNavigation(): IAuthNavigation {
        return App.uiComponent?.inject(AuthNavigation())!!
    }

    override fun homeNavigation(intent: Intent): IHomeNavigation {
        return App.uiComponent?.inject(HomeNavigation())!!
    }
}