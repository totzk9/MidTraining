package com.lig.midtraining.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.iid.FirebaseInstanceId
import com.lig.midtraining.MainApplication
import com.lig.midtraining.R
import com.lig.midtraining.data.remote.MoviesApi
import com.lig.midtraining.databinding.ActivityMainBinding
import com.lig.midtraining.model.Movie
import com.lig.midtraining.model.MovieViewModel
import com.lig.midtraining.model.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var api: MoviesApi

    @Inject
    lateinit var factory: MovieViewModelFactory
    @Inject
    lateinit var genreList: Array<String>

    private var movieAdapter = MovieAdapter(mutableListOf())

//    private val viewModel: MovieViewModel by lazy {
//        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
//            .create(MovieViewModel::class.java)
//    }

    private lateinit var viewModel: MovieViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MainApplication).component.inject(this)

        viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)

//            ViewModelProviders.of(this, factory).get(MovieViewModel::class.java)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            this.viewmodel = viewmodel
            this.lifecycleOwner = this@MainActivity
        }

        viewModel.fetchAllMovies()
        viewModel.movies.observe(this, Observer {
            movieAdapter.updateMovies(it)
        })

        recycler_view.apply {
            addItemDecoration(
                GridItemDecoration(10, 2)
            )
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = movieAdapter

            movieAdapter.onItemClick = { movie: Movie ->

                val intent = Intent(this@MainActivity, MovieActivity::class.java)
                intent.putExtra("movie", movie)
                startActivity(intent)
            }
        }

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                val token = task.result?.token
                Log.e("TOKEN", token)

            })


    }

    override fun onStart() {
        super.onStart()
        if (intent.data != null) {
            val data: Uri = intent.data!!
            data.getQueryParameter("data")?.let { openDialogFromDeepLink(it) }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter -> {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Select Genre")
                    .setItems(genreList) { _, itemPos ->
                        viewModel.getMovieByGenre(genreList, itemPos)
                    }
                    .setCancelable(true)
                    .show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openDialogFromDeepLink(data: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("You have launched the app from $data")
            .setPositiveButton("OK", null)
            .setCancelable(true)
            .show()
    }
}
