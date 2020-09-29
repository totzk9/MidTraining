package com.lig.midtraining.data.remote

import android.telecom.Call
import com.lig.midtraining.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie?api_key=c68132c2709dd8f0acd84879fb57bcab")/*&with_genres={id}*/
    fun getMovieByGenre(@Query("with_genres") id: Int): retrofit2.Call<String>

    @GET("movie?api_key=c68132c2709dd8f0acd84879fb57bcab&sort_by=popularity.desc")
    fun getMovies(): retrofit2.Call<String>
}