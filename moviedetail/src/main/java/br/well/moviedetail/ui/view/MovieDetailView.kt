package br.well.moviedetail.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import br.well.coreapp.view.ObservableView
import br.well.moviedbservice.api.model.MovieDetail
import br.well.moviedetail.R
import br.well.moviedetail.ui.view.controller.MovieDetailViewContract

class MovieDetailView(inflater: LayoutInflater, parent: ViewGroup?) :
    ObservableView<MovieDetailViewContract.Listener>(
        inflater,
        parent,
        R.layout.fragment_movie_detail
    ),
    MovieDetailViewContract {

    override fun showLoading() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bindMovieDetail(movieDetail: MovieDetail) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorMessage(message: String, function: () -> Unit) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

