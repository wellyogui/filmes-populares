package br.well.moviedbservice.api.movie

import br.well.moviedbservice.api.model.MovieDetail
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

    override fun movieDetail(movieId: Int): Single<MovieDetail> {
        return movieDataSource.movieDetail(movieId).doOnError {
            Timber.e(it, "movieDetail: ${it.message}")
        }
    }

}