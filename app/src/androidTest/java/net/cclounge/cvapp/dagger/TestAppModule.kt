package net.cclounge.cvapp.dagger

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.nhaarman.mockitokotlin2.mock
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import net.cclounge.cvapp.cv.view.CvActivity
import net.cclounge.cvapp.cv.presenter.CvPresenter
import javax.inject.Singleton

@Module
abstract class TestAppModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideApplicationContext(application: Application): Context {
            return application.applicationContext
        }

        @Provides
        @JvmStatic
        fun provideResources(context: Context): Resources {
            return context.resources
        }

        @Provides
        @JvmStatic
        fun providePicasso(): Picasso {
            return Picasso.get()
        }

        @Provides
        @JvmStatic
        @Singleton
        fun provideMockPresenter(): CvPresenter {
            return mock()
        }

    }

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun createCvActivityInjector(): CvActivity

}