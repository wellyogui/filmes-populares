package br.well.moviedetail.ui.view.usecase

import androidx.lifecycle.MutableLiveData
import br.well.coreapp.util.Resource
import br.well.moviedbservice.api.model.MovieDetail
import br.well.moviedbservice.api.movie.MovieDataSource
import br.well.moviedetail.common.provider.BaseSchedulerProvider

class MovieDetailUseCase(private val movieDataSource: MovieDataSource,
                         private val schedulerProvider: BaseSchedulerProvider) {

    val movieDetailLive = MutableLiveData<Resource<MovieDetail>>()

    fun fetchMovieDetail(movieId: Int) {
        movieDataSource.movieDetail(movieId)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe {
                movieDetailLive.value = Resource.loading(true)
            }
            .subscribe({
                movieDetailLive.value = Resource.loading(false)
                movieDetailLive.value = Resource.success(it)
            }, {
                movieDetailLive.value = Resource.loading(false)
                movieDetailLive.value = Resource.error(it.message) {fetchMovieDetail(movieId)}
            })
    }
}
