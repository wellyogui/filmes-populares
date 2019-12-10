package br.well.moviedbservice.api.movie

import br.well.moviedbservice.api.model.Movie
import rx.Single

interface MovieDataSource {
    fun movies(language: String, page: Int): Single<List<Movie>>
}
