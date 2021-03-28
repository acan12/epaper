package app.epaper.com.bolang.ui.component

import app.beelabs.com.codebase.di.component.AppComponent
import app.epaper.com.bolang.ui.component.impl.IHomeNavigation
import app.epaper.com.bolang.ui.component.impl.INavigation
import app.epaper.com.bolang.ui.component.module.NavModule
import com.pede.emoney.ui.component.scope.UIScope
import dagger.Component

@UIScope
@Component(modules = [NavModule::class], dependencies = [AppComponent::class])
interface UIComponent {
    fun inject(iNavigation: INavigation): INavigation
    fun inject(iHomeNavigation: IHomeNavigation): IHomeNavigation

    fun newSupportSubcomponent() : SupportSubComponent
}

