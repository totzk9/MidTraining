package com.lig.midtraining.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lig.midtraining.data.remote.MovieRepository
import com.lig.midtraining.data.remote.MoviesApi
import com.lig.midtraining.model.Movie
import kotlinx.coroutines.*

class MovieViewModel (private val repository: MovieRepository) : ViewModel() {

    var movies = MutableLiveData<List<Movie>>()

    fun fetchAllMovies() {
        repository.getAllMovies({
            Log.e("VIEWMODEL", it.toString())
            movies.value = it
        }, {
            Log.e("INTERNET ERROR", it.message)
        })
    }

    fun getMovieByGenre(genreList: Array<String>, itemPos: Int) {
        val id: Int = when (genreList[itemPos]) {
            "Action" -> 28
            "Adventure" -> 12
            "Animation" -> 16
            "Comedy" -> 35
            "Crime" -> 80
            "Documentary" -> 99
            "Drama" -> 18
            "Family" -> 10751
            "Fantasy" -> 14
            "History" -> 36
            "Horror" -> 27
            "Music" -> 10402
            "Mystery" -> 9648
            "Romance" -> 10749
            "Science Fiction" -> 878
            "TV Movie" -> 10770
            "Thriller" -> 53
            "War" -> 10752
            "Western" -> 37
            else -> 28
        }
        repository.getMoviesByGenre(id, {
            movies.value = it
        }, {
            Log.e("INTERNET ERROR", "FetchAllMovies")
        })
    }


//    fun fetchAllMovies() {
//        viewModelScope.launch(Dispatchers.Default) {
//            Log.e("Diri 1", "Expected FetchAllMovies to pre fetch")
//            MovieRepository().fetchAllMovies {
//                movies.postValue(it)
//            }
//
////            movies.postValue(MovieRepository().fetchAllMovies())
////            movies = MovieRepository().getLiveData()
//        }
//    }

//    fun getMovieByGenre(genreList: Array<String>, itemPos: Int) {
//        val id: Int = when (genreList[itemPos]) {
//            "Action" -> 28
//            "Adventure" -> 12
//            "Animation" -> 16
//            "Comedy" -> 35
//            "Crime" -> 80
//            "Documentary" -> 99
//            "Drama" -> 18
//            "Family" -> 10751
//            "Fantasy" -> 14
//            "History" -> 36
//            "Horror" -> 27
//            "Music" -> 10402
//            "Mystery" -> 9648
//            "Romance" -> 10749
//            "Science Fiction" -> 878
//            "TV Movie" -> 10770
//            "Thriller" -> 53
//            "War" -> 10752
//            "Western" -> 37
//            else -> 28
//        }
//        Log.e("Diri GENRE", "$id ")
//
//        viewModelScope.launch(Dispatchers.Default) {
//            MovieRepository().fetchByGenre(id) {
//                movies.postValue(it)
//            }
//        }
//
//    }
}