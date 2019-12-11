package br.well.movies.ui.view.controller

import androidx.lifecycle.Lifecycle
import br.well.coreapp.util.ResourceState.*
import br.well.coreapp.view.LiveController
import br.well.movies.ui.usecase.MovieUseCase

class MoviesController(private val movieUseCase: MovieUseCase, private val lifecycle: Lifecycle) :
    LiveController<MoviesViewContract.Listener, MoviesViewContract>(),
    MoviesViewContract.Listener {

    var currentPage: Int = 1

    override fun onCreate(view: MoviesViewContract) {
        super.onCreate(view)
        viewContract.registerListener(this)
    }

    fun onStart() {
        movieUseCase.fetchPopularMovies(1)
    }

    override fun initViews() {
        observeLive()
    }

    override fun onStop() {
        viewContract.unregisterListener(this)
    }

    override fun onResume() {
        viewContract.registerListener(this)
    }

    override fun observeLive() {
        movieUseCase.moviesLiveData.observe({ lifecycle }, {
            when (it.status) {
                LOADING -> {
                    if (currentPage == 1) {
                        when (it.loading) {
                            true -> viewContract.showLoading()
                            false -> viewContract.hideLoading()
                        }
                    } else {
                        when (it.loading) {
                            true -> viewContract.showListLoad()
                            false -> viewContract.hideListLoad()
                        }
                    }
                }
                SUCCESS -> {
                    viewContract.bindMovies(it.data!!)
                }
                ERROR -> {
                    viewContract.showMessageError(it.message!!, it.callback!!)
                }
            }
        })
    }

    override fun loadNextPage() {
        currentPage += 1
        movieUseCase.fetchPopularMovies(currentPage)
    }

}
