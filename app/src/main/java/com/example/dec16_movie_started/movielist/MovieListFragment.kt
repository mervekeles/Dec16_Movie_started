package com.example.dec16_movie_started.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dec16_movie_started.R
import com.example.dec16_movie_started.databinding.FragmentMovieListBinding

class MovieListFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMovieListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_list, container, false
        )
        val application = requireNotNull(this.activity).application
        val viewModel: MovieListViewModel by viewModels(){ MovieListViewModelFactory(application) }

        binding.rv.adapter = MovieListAdapter()

        binding.lifecycleOwner = viewLifecycleOwner

        // Giving the binding access to the OverviewViewModel
        binding.vm = viewModel
            return binding.root
    }
}