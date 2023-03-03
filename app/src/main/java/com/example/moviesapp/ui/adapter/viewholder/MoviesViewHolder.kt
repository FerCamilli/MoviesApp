package com.example.moviesapp.ui.adapter.viewholder

import android.content.Context
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.ItemListMovieBinding
import com.example.moviesapp.di.util.MOVIE_API_IMAGES_BASE_URL
import com.example.moviesapp.di.util.MOVIE_API_IMAGES_W185_SIZE_PATH
import com.example.moviesapp.domain.model.Movie

class MoviesViewHolder(
    private val binding: ItemListMovieBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        val fullUri = Uri.parse(MOVIE_API_IMAGES_BASE_URL + MOVIE_API_IMAGES_W185_SIZE_PATH + movie.posterUrlPath)
        Glide.with(context)
            .load(fullUri)
            .into(binding.itemMoviesImageView)

        with(binding) {
            movieNameTextView.text = movie.title
            movieOverviewTextView.text = movie.overview
            movieScoreTextView.text = movie.voteScore.toString()
        }
    }
}
