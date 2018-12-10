package net.cclounge.cvapp.dagger

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import net.cclounge.cvapp.CvApplication
import net.cclounge.cvapp.cv.presenter.CvPresenter
import javax.inject.Inject

class TestApplication : CvApplication() {

    @Inject
    lateinit var mockPresenter: CvPresenter

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerTestAppComponent.builder().application(this).build()
        component.inject(this)
        return component
    }

}
