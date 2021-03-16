package com.ayeshaazeema.nonton.service

import com.ayeshaazeema.nonton.model.ResponseMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie")
    fun getMovieData(
        @Query("apiKey") apiKey: String?
    ): Call<ResponseMovie>
}