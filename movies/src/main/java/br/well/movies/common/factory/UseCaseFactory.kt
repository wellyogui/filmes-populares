package br.well.movies.common.factory

import br.well.movies.common.provider.BaseSchedulerProvider
import br.well.moviedbservice.api.DataSourceFactory
import br.well.movies.ui.usecase.MovieUseCase

class UseCaseFactory(private val schedulerProvider: BaseSchedulerProvider) {
    fun provideMovieUseCase(): MovieUseCase {
        return MovieUseCase(DataSourceFactory.provideMovieDataSource(), schedulerProvider)
    }
}
