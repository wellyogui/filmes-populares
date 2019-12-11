package br.well.moviedetail.ui.view.controller

import br.well.coreapp.view.ObservableViewContract
import br.well.coreapp.view.base.BaseListener
import br.well.moviedbservice.api.model.MovieDetail

interface MovieDetailViewContract: ObservableViewContract<MovieDetailViewContract.Listener> {

    interface Listener: BaseListener {

    }

    fun showLoading()
    fun bindMovieDetail(movieDetail: MovieDetail)
    fun hideLoading()
    fun showErrorMessage(message: String, function: () -> Unit)
}
