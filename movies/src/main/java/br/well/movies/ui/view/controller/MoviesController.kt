package br.well.movies.ui.view.controller

import androidx.lifecycle.Lifecycle
import br.well.coreapp.util.ResourceState.*
import br.well.coreapp.view.LiveController
import br.well.movies.ui.usecase.MovieUseCase

class MoviesController(private val movieUseCase: MovieUseCase, private val lifecycle: Lifecycle): LiveController<MoviesViewContract.Listener, MoviesViewContract>(),
    MoviesViewContract.Listener {


    override fun onCreate(view: MoviesViewContract) {
        super.onCreate(view)
        viewContract.registerListener(this)
    }

    fun onStart() {
        movieUseCase.fetchPopularMovies("BR", 1)
    }

    override fun observeLive() {
        movieUseCase.moviesLiveData.observe({lifecycle} , {
            when(it.status) {
                LOADING -> {
                    when(it.loading) {
                        true -> viewContract.showLoading()
                        false -> viewContract.hideLoading()
                    }
                }
                SUCCESS -> {
                    viewContract.bindMovies()
                }
                ERROR -> {
                    viewContract.showMessageError()
                }
            }
        })
    }

    override fun initViews() {
        observeLive()
    }

    override fun onStop() {
        viewContract.unregisterListener(this)
    }

    override fun onResume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
