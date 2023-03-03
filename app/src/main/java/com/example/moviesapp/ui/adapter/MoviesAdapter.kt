package com.example.moviesapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.ItemListMovieBinding
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.ui.adapter.viewholder.MoviesViewHolder

class MoviesAdapter(
    private val context: Context,
    private val movieList: ArrayList<Movie>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MoviesViewHolder(
            ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            context
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MoviesViewHolder).bind(movieList[position])
    }

    override fun getItemCount() = movieList.size
}
