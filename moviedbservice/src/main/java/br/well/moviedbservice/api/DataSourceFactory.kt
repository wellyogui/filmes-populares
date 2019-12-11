package br.well.moviedbservice.api

import br.well.moviedbservice.api.movie.MovieRepository
import br.well.moviedbservice.api.movie.remote.MovieApi

object DataSourceFactory {
    fun provideMovieDataSource() = MovieRepository.getInstance(MovieApi)
}
