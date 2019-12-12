package br.well.movies.ui.view.controller

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.well.coreapp.view.base.BaseFragment
import br.well.movies.common.factory.ControllerFactory
import br.well.movies.common.provider.AppProvider

class MoviesFragment :
    BaseFragment<MoviesViewContract, MoviesController, AppProvider, ControllerFactory>() {

    override val viewContract: MoviesViewContract by lazy {
        appProvider.viewFactory.provideMovieView(view?.parent as ViewGroup?)
    }
    override val controller: MoviesController by lazy {
        controllerFactory.provideMoviesController(listener)
    }
    override val appProvider: AppProvider by lazy {
        AppProvider((requireActivity() as AppCompatActivity))
    }
    override val controllerFactory: ControllerFactory by lazy {
        ControllerFactory(appProvider.useCaseFactory, this)
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }

    override fun onStop() {
        super.onStop()
        controller.onStop()
    }

    override fun onResume() {
        super.onResume()
        controller.onResume()
    }

    override fun onStart() {
        super.onStart()
        controller.onStart()
    }
}