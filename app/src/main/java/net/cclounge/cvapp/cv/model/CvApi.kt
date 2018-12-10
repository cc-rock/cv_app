package net.cclounge.cvapp.cv.model

import io.reactivex.Single
import retrofit2.http.GET

interface CvApi {
    @GET("raw")
    fun getCv(): Single<Cv>
}