package com.lig.midtraining.data.remote

import android.util.Log
import com.lig.midtraining.model.Movie
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject


interface MovieRepository {

    fun getAllMovies(onSuccess: (movieList: List<Movie>) -> Unit, onFailure: (t: Throwable) -> Unit)

    fun getMoviesByGenre(id: Int, onSuccess: (movieList: List<Movie>) -> Unit, onFailure: (t: Throwable) -> Unit)

//    fun fetchAllMovies(onListen: (List<Movie>) -> Unit) {
//        SingletonRequest({
//            onListen(extractJSON(it))
//        }, {
//            Log.e("error fetching", it)
//        }).makeRequest("movie?api_key=c68132c2709dd8f0acd84879fb57bcab&sort_by=popularity.desc")
//    }
//
//    private fun extractJSON(jsonObject: JSONObject): List<Movie> {
//
//        val movieList: MutableList<Movie> = mutableListOf()
//
//        val result = jsonObject.getJSONArray("results") as JSONArray
//        Log.e("jsonObject", result.toString())
//        for (i in 0 until result.length()) {
//
//            movieList.add(
//                Movie(
//                    (result[i] as JSONObject).getString("title"),
//                    (result[i] as JSONObject).getString("overview"),
//                    (result[i] as JSONObject).getString("backdrop_path"),
//                    (result[i] as JSONObject).getString("poster_path")
//                )
//            )
//        }
//
//        return movieList
//    }
//
//    fun fetchByGenre(id: Int, onListen: (List<Movie>) -> Unit) {
//        SingletonRequest({
//            Log.e("Fetch result", it.toString())
//            onListen(extractJSON(it))
//        }, {
//            Log.e("error fetching", it)
//        }).makeRequest("movie?api_key=c68132c2709dd8f0acd84879fb57bcab&with_genres=$id")
//    }

}