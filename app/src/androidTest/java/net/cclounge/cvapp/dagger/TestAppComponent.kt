package net.cclounge.cvapp.dagger

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import net.cclounge.cvapp.CvApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [TestAppModule::class, AndroidInjectionModule::class])
interface TestAppComponent: AndroidInjector<CvApplication> {

    fun inject(testApplication: TestApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestAppComponent
    }

}