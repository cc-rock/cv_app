package net.cclounge.cvapp.cv

import io.reactivex.Observable
import io.reactivex.Single
import net.cclounge.cvapp.cv.model.Cv
import net.cclounge.cvapp.cv.view.CvScreenViewModel
import retrofit2.http.GET

interface CvModel {
    @GET("raw")
    fun getCv(): Single<Cv>
}

interface CvPresenter {
    fun loadData(): Observable<CvScreenViewModel>
}
