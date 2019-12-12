package br.well.movies.common.factory

import androidx.lifecycle.LifecycleOwner
import br.well.coreapp.ScreenNavigator
import br.well.movies.ui.view.controller.MoviesController

class ControllerFactory(
        private val useCaseFactory: UseCaseFactory,
        private val lifecycleOwner: LifecycleOwner
) {
        fun provideMoviesController(screenNavigator: ScreenNavigator): MoviesController {
                return MoviesController(useCaseFactory.provideMovieUseCase(), lifecycleOwner.lifecycle, screenNavigator)
        }
}
