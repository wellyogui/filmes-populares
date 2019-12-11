package br.well.movies.common.factory

import androidx.lifecycle.LifecycleOwner
import br.well.movies.common.provider.AppProvider
import br.well.movies.ui.view.controller.MoviesController

class ControllerFactory(
        private val useCaseFactory: UseCaseFactory,
        private val appProvider: AppProvider,
        private val lifecycleOwner: LifecycleOwner
) {
        fun provideMoviesController(): MoviesController {
                return MoviesController(useCaseFactory.provideMovieUseCase(), lifecycleOwner.lifecycle)
        }
}
