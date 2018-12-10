package net.cclounge.cvapp.cv.presenter

import io.reactivex.Observable
import net.cclounge.cvapp.cv.view.CvScreenViewModel

interface CvPresenter {
    fun loadData(): Observable<CvScreenViewModel>
}