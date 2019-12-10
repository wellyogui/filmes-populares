package br.well.movies.ui.usecase

import androidx.lifecycle.MutableLiveData
import br.well.coreapp.provider.BaseSchedulerProvider
import br.well.coreapp.util.Resource
import br.well.moviedbservice.api.model.Movie
import br.well.moviedbservice.api.movie.MovieDataSource

class MovieUseCase(private val movieDataSource: MovieDataSource,
                   private val schedulerProvider: BaseSchedulerProvider) {

    val moviesLiveData = MutableLiveData<Resource<List<Movie>>>()

    fun fetchPopularMovies(language: String, page: Int) {
        movieDataSource.movies(language, page)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { moviesLiveData.value = Resource.loading(true) }
                .subscribe({
                    moviesLiveData.value = Resource.loading(false)
                    moviesLiveData.value = Resource.success(it)
                }, {
                    moviesLiveData.value = Resource.loading(false)
                    moviesLiveData.value =
                            Resource.error(it.message) { fetchPopularMovies(language, page) }
                })
    }

}