package net.cclounge.cvapp

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import net.cclounge.cvapp.dagger.DaggerAppComponent

open class CvApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}