package net.cclounge.cvapp.cv

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import net.cclounge.cvapp.cv.model.CvApi
import net.cclounge.cvapp.cv.presenter.CvPresenterImpl
import net.cclounge.cvapp.cv.presenter.CvPresenter
import net.cclounge.cvapp.dagger.ActivityScope
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
abstract class DaggerModule {

    @Binds
    @ActivityScope
    abstract fun bindPresenter(cvPresenterImpl: CvPresenterImpl): CvPresenter

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/cc-rock/ec339c94ba8ffa9428c10c7f84480ebd/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideApi(retrofit: Retrofit): CvApi {
            return retrofit.create(CvApi::class.java)
        }

        @JvmStatic
        @Provides
        fun provideRxScheduler(): Scheduler {
            return Schedulers.io()
        }
    }

}