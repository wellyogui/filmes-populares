package br.well.movies.ui.view.controller

import br.well.coreapp.view.ObservableViewContract
import br.well.coreapp.view.base.BaseListener
import br.well.moviedbservice.api.model.Movies

interface MoviesViewContract : ObservableViewContract<MoviesViewContract.Listener> {
    interface Listener : BaseListener {
        fun loadNextPage()
        fun onMovieClicked(movieId: Int)
    }

    fun showLoading()
    fun bindMovies(movie: Movies)
    fun hideLoading()
    fun showMessageError(message: String, function: () -> Unit)
    fun showListLoad()
    fun hideListLoad()

}
