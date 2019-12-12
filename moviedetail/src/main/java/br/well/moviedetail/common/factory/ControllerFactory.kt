package br.well.moviedetail.common.factory

import androidx.lifecycle.LifecycleOwner
import br.well.coreapp.ScreenNavigator
import br.well.moviedetail.ui.view.controller.MovieDetailController

class ControllerFactory(
        private val useCaseFactory: UseCaseFactory,
        private val lifecycleOwner: LifecycleOwner
) {
    fun provideMovieDetailController(movieId: Int, listener: ScreenNavigator): MovieDetailController = MovieDetailController(
        useCaseFactory.provideMovieDetailUseCase(),
        lifecycleOwner.lifecycle,
        movieId, listener
    )

}
