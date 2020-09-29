package com.lig.midtraining.di

import com.google.android.gms.common.api.Api
import com.lig.midtraining.data.remote.MovieRepository
import com.lig.midtraining.data.remote.MoviesApi
import com.lig.midtraining.model.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun providesMainViewModelFactory(repository: MovieRepository): MovieViewModelFactory {
        return MovieViewModelFactory(repository)
    }
}