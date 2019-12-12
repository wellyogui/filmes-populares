package br.well.moviedetail.ui.view.controller

import androidx.lifecycle.Lifecycle
import br.well.coreapp.util.ResourceState
import br.well.coreapp.view.LiveController
import br.well.moviedetail.ui.view.usecase.MovieDetailUseCase

class MovieDetailController(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val lifecycle: Lifecycle,
    private val movieId: Int
) : LiveController<MovieDetailViewContract.Listener, MovieDetailViewContract>(), MovieDetailViewContract.Listener {


    override fun onCreate(view: MovieDetailViewContract) {
        super.onCreate(view)
        viewContract.registerListener(this)
    }

    override fun onStop() {
        viewContract.unregisterListener(this)
    }

    override fun onResume() {
        viewContract.registerListener(this)
    }

    fun onStart() {
        movieDetailUseCase.fetchMovieDetail(movieId)
    }

    override fun observeLive() {
        movieDetailUseCase.movieDetailLive.observe({lifecycle}, {
            when(it.status) {
                ResourceState.LOADING -> {
                    when(it.loading) {
                        true -> viewContract.showLoading()
                        false -> viewContract.hideLoading()
                    }
                }
                ResourceState.SUCCESS -> {
                    viewContract.bindMovieDetail(it.data!!)
                }
                ResourceState.ERROR -> {
                    viewContract.showErrorMessage(it.message!!) {it.callback}
                }
            }
        })
    }

    override fun initViews() {
        // UNUSED
    }

    override fun onBackPressed() {
        viewContract.onBackPressed()
    }
}