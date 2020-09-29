package com.lig.midtraining.di

import com.lig.midtraining.data.remote.MovieRepository
import com.lig.midtraining.data.remote.MoviesApi
import com.lig.midtraining.model.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesUserRepository(api: MoviesApi): MovieRepository {
        return MovieRepositoryImpl(api)
    }
}