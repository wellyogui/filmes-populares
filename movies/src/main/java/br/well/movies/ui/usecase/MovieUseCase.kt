package br.well.movies.ui.usecase

import androidx.lifecycle.MutableLiveData
import br.well.coreapp.BaseSchedulerProvider
import br.well.coreapp.util.Resource
import br.well.moviedbservice.api.model.Movies
import br.well.moviedbservice.api.movie.MovieDataSource

class MovieUseCase(private val movieDataSource: MovieDataSource,
                   private val schedulerProvider: BaseSchedulerProvider
) {

    val moviesLiveData = MutableLiveData<Resource<Movies>>()

    fun fetchPopularMovies(page: Int) {
        movieDataSource.movies(page)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { moviesLiveData.value = Resource.loading(true) }
                .subscribe({
                    moviesLiveData.value = Resource.loading(false)
                    moviesLiveData.value = Resource.success(it)
                }, {
                    moviesLiveData.value = Resource.loading(false)
                    moviesLiveData.value =
                            Resource.error(it.message) { fetchPopularMovies(page) }
                })
    }

}