package com.example.dec16_movie_started.moviedetail

import com.example.dec16_movie_started.data.Movie

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MovieDetailViewModel(movieKey: Int, application: Application) : AndroidViewModel(application) {
    //we extend from AndroidViewModel instead of ViewModel because it's the version that can reference the application context to instantiate the database in a lifecycle-aware way.


    val app = application

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie


}

class MovieDetailViewModelFactory(private val movieKey: Int, private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(movieKey, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
