package br.well.movies.ui.view.controller

import br.well.coreapp.view.ObservableViewContract
import br.well.coreapp.view.base.BaseListener
import br.well.moviedbservice.api.model.Movies

interface MoviesViewContract : ObservableViewContract<MoviesViewContract.Listener> {
    interface Listener : BaseListener {
        fun loadNextPage()

    }

    fun showLoading()
    fun bindMovies(movie: Movies)
    fun hideLoading()
    fun showMessageError()
    fun showListLoad()
    fun hideListLoad()

}
