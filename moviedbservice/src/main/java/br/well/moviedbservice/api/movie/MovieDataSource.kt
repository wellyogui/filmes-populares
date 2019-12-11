package br.well.moviedbservice.api.movie

import br.well.moviedbservice.api.model.Movies
import rx.Single

interface MovieDataSource {
    fun movies(page: Int): Single<Movies>
}
