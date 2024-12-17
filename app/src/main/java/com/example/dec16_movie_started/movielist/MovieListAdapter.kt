package com.example.dec16_movie_started.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dec16_movie_started.data.Movie
import com.example.dec16_movie_started.databinding.ListItemMovieBinding

class MovieListAdapter(): ListAdapter<Movie, MovieListAdapter.ItemViewHolder>(MovieDiffCallback()) {

    class ItemViewHolder(val binding: ListItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object{
            private const val TAG = "Adapter"
            fun from(parent: ViewGroup): ItemViewHolder{
                val binding = ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ItemViewHolder(binding)
            }
        }

        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ItemViewHolder {
        return MovieListAdapter.ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)


    }



}

class MovieDiffCallback: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}