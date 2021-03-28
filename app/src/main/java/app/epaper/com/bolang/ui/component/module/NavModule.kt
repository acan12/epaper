package app.epaper.com.bolang.ui.component.module

import app.epaper.com.bolang.ui.component.impl.INavigation
import app.epaper.com.bolang.ui.component.manager.NavigationManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavModule {

    @Singleton
    @Provides
    fun provideNavigation(): INavigation {
        return NavigationManager()
    }


}