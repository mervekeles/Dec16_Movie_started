package com.example.dec16_movie_started.movielist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.dec16_movie_started.data.Movie
import com.example.dec16_movie_started.network.MoviesApi
import kotlinx.coroutines.launch

class MovieListViewModel (application: Application): AndroidViewModel(application) {
    //Lets create a live data to store the response fisrt
    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()
    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response
    /**
     * Call getMostPopularMovies() on init so we can display status immediately.
     */

    private val _movies = MutableLiveData<List<Movie>>()
    val movies :LiveData<List<Movie>>
        get() = _movies

    private fun getMovies() {
        viewModelScope.launch {

            try {
                val response = MoviesApi.retrofitService.getMostPopular()

                _response.value =
                    "Success: ${response.results.size} Movies retrieved"
                _movies.value = response.results
                //_status.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                //_status.value = MovieApiStatus.ERROR
                _movies.value = ArrayList()
            }
        }
    }
    init{
        getMovies()
    }
}


class MovieListViewModelFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}