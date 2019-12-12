package br.well.moviedetail.ui.view.controller

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.well.coreapp.view.base.BaseFragment
import br.well.moviedetail.common.factory.ControllerFactory
import br.well.moviedetail.common.provider.AppProvider
import timber.log.Timber

class MovieDetailFragment :
    BaseFragment<MovieDetailViewContract, MovieDetailController, AppProvider, ControllerFactory>() {

    var movieId: Int = 0

    override val viewContract: MovieDetailViewContract by lazy {
        appProvider.viewFactory.provideMovieDetailView(view?.parent as ViewGroup?)
    }
    override val controller: MovieDetailController by lazy {
        controllerFactory.provideMovieDetailController(movieId, listener)
    }

    override val appProvider: AppProvider by lazy {
        AppProvider(
            (requireActivity() as AppCompatActivity)
        )
    }

    override val controllerFactory: ControllerFactory by lazy {
        ControllerFactory(appProvider.useCaseFactory, this)
    }

    companion object {
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun newInstance(movieId: Int) = MovieDetailFragment().run {
            arguments = Bundle().apply {
                putInt(EXTRA_MOVIE_ID, movieId)
            }
            return@run this
        }
    }

    override fun initData() {
        super.initData()
        arguments?.getInt(EXTRA_MOVIE_ID)?.let {
            movieId = it
        } ?: run {
            Timber.e("Bundle %s is required and was not found", EXTRA_MOVIE_ID)
            activity?.finish()
        }

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