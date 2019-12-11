package br.well.movies.ui.view.controller

import br.well.coreapp.view.ObservableViewContract
import br.well.coreapp.view.base.BaseListener

interface MoviesViewContract : ObservableViewContract<MoviesViewContract.Listener> {
    interface Listener : BaseListener {

    }

    fun showLoading()
    fun bindMovies()
    fun hideLoading()
    fun showMessageError()

}
