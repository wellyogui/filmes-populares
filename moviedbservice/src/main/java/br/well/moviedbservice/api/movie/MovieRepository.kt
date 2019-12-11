package br.well.moviedbservice.api.movie

import br.well.moviedbservice.api.model.Movies
import rx.Single
import timber.log.Timber


class MovieRepository(private val movieDataSource: MovieDataSource) : MovieDataSource {

    companion object {
        private var INSTANCE: MovieRepository? = null

        @JvmStatic
        fun getInstance(remoteDataSource: MovieDataSource) = INSTANCE
                ?: synchronized(MovieRepository::class.java) {
                    INSTANCE
                            ?: MovieRepository(
                                    remoteDataSource
                            )
                                    .also { INSTANCE = it }
                }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun movies(page: Int): Single<Movies> {
        return movieDataSource.movies(page).doOnError {
            Timber.e(it, "movies: ${it.message}")
        }
    }

}