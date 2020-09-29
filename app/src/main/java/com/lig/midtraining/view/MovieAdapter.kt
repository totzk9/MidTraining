package com.lig.midtraining.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lig.midtraining.model.Movie
import com.lig.midtraining.R
import com.lig.midtraining.databinding.ItemMovieBinding
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val movies: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.Item>() {

    var onItemClick: ((Movie/*, ImageView*/) -> Unit)? = null

    fun updateMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Item(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.bind(movies[position])
        holder.itemMovieBinding.movie = movies[position]
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class Item(val itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {

        fun bind(movie: Movie) = with(itemView) {

            this.setOnClickListener {
                onItemClick?.invoke(movie/*, img_movie*/)
            }
        }
    }
}