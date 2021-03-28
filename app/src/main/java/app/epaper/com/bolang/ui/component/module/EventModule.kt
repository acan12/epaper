package app.epaper.com.bolang.ui.component.module

import app.epaper.com.bolang.ui.component.IEvent
import app.epaper.com.bolang.ui.component.manager.EventManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class EventModule {

    @Singleton
    @Provides
    fun provideEvent(): IEvent {
        return EventManager()
    }


}