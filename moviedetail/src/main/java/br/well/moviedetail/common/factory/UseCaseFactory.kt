package br.well.moviedetail.common.factory

import br.well.moviedbservice.api.DataSourceFactory
import br.well.coreapp.BaseSchedulerProvider
import br.well.moviedetail.ui.view.usecase.MovieDetailUseCase

class UseCaseFactory(private val schedulerProvider: BaseSchedulerProvider) {
    fun provideMovieDetailUseCase(): MovieDetailUseCase = MovieDetailUseCase(DataSourceFactory.provideMovieDataSource(), schedulerProvider)
}
