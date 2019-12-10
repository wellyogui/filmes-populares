package br.well.moviedbservice.api.movie.remote

import br.well.moviedbservice.api.MovieDbApi
import br.well.moviedbservice.api.model.Movie
import br.well.moviedbservice.api.movie.MovieDataSource
import rx.Single
import timber.log.Timber


const val API_KEY = "13e45016e38e14cf41a640157efba508"

object MovieApi : MovieDataSource {

    private val movieService by lazy {
        MovieDbApi.retrofit.create(MovieService::class.java)
    }

    override fun movies(language: String, page: Int): Single<List<Movie>> {
        return movieService.movie(
                API_KEY, language, page
        ).doOnError {
            Timber.e(it, "movies: ${it.message}")
        }
    }

}