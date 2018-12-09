package net.cclounge.cvapp.dagger

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import net.cclounge.cvapp.CvApplication

@Component(modules = [AppModule::class, AndroidInjectionModule::class])
interface AppComponent: AndroidInjector<CvApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}