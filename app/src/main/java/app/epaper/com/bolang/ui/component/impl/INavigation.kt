package app.epaper.com.bolang.ui.component.impl

import android.content.Intent

interface INavigation : IBaseNavigation {
    fun authNavigation(): IAuthNavigation

    fun homeNavigation(intent: Intent): IHomeNavigation
}