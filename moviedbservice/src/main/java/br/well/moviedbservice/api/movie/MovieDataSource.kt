package br.well.moviedbservice.api.movie

import br.well.moviedbservice.api.model.MovieDetail
import br.well.moviedbservice.api.model.Movies
import rx.Single

interface MovieDataSource {
    fun movies(page: Int): Single<Movies>
    fun movieDetail(movieId: Int): Single<MovieDetail>
}
