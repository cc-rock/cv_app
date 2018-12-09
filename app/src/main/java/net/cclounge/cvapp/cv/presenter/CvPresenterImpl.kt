package net.cclounge.cvapp.cv.presenter

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import net.cclounge.cvapp.cv.CvModel
import net.cclounge.cvapp.cv.CvPresenter
import net.cclounge.cvapp.cv.view.CvScreenState
import net.cclounge.cvapp.cv.view.CvScreenViewModel
import javax.inject.Inject

class CvPresenterImpl @Inject constructor(private val model: CvModel,
                                          private val converter: CvScreenConverter,
                                          private val scheduler: Scheduler): CvPresenter {

    override fun loadData(): Observable<CvScreenViewModel> {
        return model.getCv()
            .map {
                converter.convert(it)
            }.toObservable()
            .startWith(CvScreenViewModel(null, CvScreenState.LOADING)) // Send the loading view model first
            .onErrorReturnItem(CvScreenViewModel(null, CvScreenState.ERROR))
            .subscribeOn(scheduler)
    }

}