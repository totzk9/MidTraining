package com.lig.midtraining.di

import com.lig.midtraining.data.remote.MovieRepository
import com.lig.midtraining.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
//    fun repository(): MovieRepository
//    fun inject(viewModel: MovieViewModel)
}