package app.epaper.com.bolang.ui.component.impl

interface INavigation : IBaseNavigation {
    fun authNavigation(): IAuthNavigation

    fun homeNavigation(): IHomeNavigation
}