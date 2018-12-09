package net.cclounge.cvapp.dagger

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.squareup.picasso.Picasso
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.android.DaggerApplication
import net.cclounge.cvapp.cv.CvActivity

@Module
abstract class AppModule {

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

    }

    @ActivityScope
    @ContributesAndroidInjector(modules = [net.cclounge.cvapp.cv.DaggerModule::class])
    abstract fun createCvActivityInjector(): CvActivity

}