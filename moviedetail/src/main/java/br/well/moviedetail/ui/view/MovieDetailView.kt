package br.well.moviedetail.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import br.well.coreapp.ext.*
import br.well.coreapp.view.ObservableView
import br.well.moviedbservice.api.model.MovieDetail
import br.well.moviedetail.R
import br.well.moviedetail.ui.view.controller.MovieDetailViewContract
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie_detail.view.*

class MovieDetailView(inflater: LayoutInflater, parent: ViewGroup?) :
    ObservableView<MovieDetailViewContract.Listener>(
        inflater,
        parent,
        R.layout.fragment_movie_detail
    ),
    MovieDetailViewContract {

    override fun showLoading() {
        rootView.loadingView.visible()
    }

    override fun bindMovieDetail(movieDetail: MovieDetail) {
        setupToolbar(movieDetail.title)
        with(rootView) {
            movieNameView.text = movieDetail.title
            movieReleaseDateView.text = "Data de lançamento: ${movieDetail.releaseDate.toHumanDate()}"
            val productionNames = arrayListOf<String>()
            movieDetail.productionCompanies.forEach { productionNames.add(it.name) }
            movieProductionView.text = "Produção: ${productionNames.joinToString()}"
            movieRatingView.text = "Avaliação: ${movieDetail.voteAverage}"
            movieBannerView.loadImage(movieDetail.backdropPath)
            moviePosterView.loadImage(movieDetail.posterPath)
        }
    }

    override fun hideLoading() {
        with(rootView) {
            movieInfoCardView.visible()
            loadingView.gone()
        }
    }

    override fun showErrorMessage(message: String, function: () -> Unit) {
        rootView.showSnackBar(message, Snackbar.LENGTH_LONG, "Tentar Novamente", function)
    }

    private fun setupToolbar(movieTitle: String) {
        rootView.toolbar.title = movieTitle
        activity.setActionBar(rootView.toolbar)
        activity.actionBar!!.setDisplayShowHomeEnabled(true)
        activity.actionBar!!.setDisplayHomeAsUpEnabled(true)
        activity.actionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        rootView.toolbar.setNavigationOnClickListener {
            listeners.forEach {
                it.onBackPressed()
            }
        }
    }

    override fun onBackPressed() {
        activity.onBackPressed()
    }
}

