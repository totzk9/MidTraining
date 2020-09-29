package com.lig.midtraining.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lig.midtraining.data.remote.MovieRepository
import com.lig.midtraining.data.remote.MoviesApi
import javax.inject.Inject

class MovieViewModelFactory @Inject constructor (private val repository: MovieRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}