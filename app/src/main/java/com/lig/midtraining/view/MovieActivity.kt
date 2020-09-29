package com.lig.midtraining.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.lig.midtraining.R
import com.lig.midtraining.databinding.ActivityMovieBinding
import com.lig.midtraining.model.Movie

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding

    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)

        movie = intent.getParcelableExtra("movie") as Movie

        binding.movie = movie
        binding.lifecycleOwner = this


    }

}
