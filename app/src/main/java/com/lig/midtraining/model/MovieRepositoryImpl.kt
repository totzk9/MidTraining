package com.lig.midtraining.model

import android.util.Log
import com.lig.midtraining.data.remote.MovieRepository
import com.lig.midtraining.data.remote.MoviesApi
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImpl(private val api: MoviesApi) : MovieRepository {

    override fun getAllMovies(
        onSuccess: (movieList: List<Movie>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
       api.getMovies().enqueue(object : Callback<String> {
           override fun onResponse(call: Call<String>, response: Response<String>) {
               response.body()?.let { result ->
//                   Log.e("MOVIES", movieList.toString())

                   onSuccess.invoke(extractJSON(result))
               }
           }

           override fun onFailure(call: Call<String>, t: Throwable) {
               onFailure.invoke(t)
           }
       })
    }

    override fun getMoviesByGenre(
        id: Int,
        onSuccess: (movieList: List<Movie>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        api.getMovieByGenre(id).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                response.body()?.let { result ->
                    onSuccess.invoke(extractJSON(result))
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                onFailure.invoke(t)
            }
        })
    }

    private fun extractJSON(resultString: String): List<Movie> {
        val movieList: MutableList<Movie> = mutableListOf()

        val jsonObject = JSONObject(resultString)
        val result = jsonObject.getJSONArray("results") as JSONArray
        Log.e("jsonObject", result.toString())
        for (i in 0 until result.length()) {

            movieList.add(
                Movie(
                    (result[i] as JSONObject).getString("title"),
                    (result[i] as JSONObject).getString("overview"),
                    (result[i] as JSONObject).getString("backdrop_path"),
                    (result[i] as JSONObject).getString("poster_path")
                )
            )
        }

        return movieList
    }
}