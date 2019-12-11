package br.well.moviedetail.common.factory

import android.view.LayoutInflater
import android.view.ViewGroup
import br.well.moviedetail.ui.view.MovieDetailView
import br.well.moviedetail.ui.view.controller.MovieDetailViewContract

class ViewFactory(private val layoutInflater: LayoutInflater) {
    fun provideMovieDetailView(viewGroup: ViewGroup?): MovieDetailViewContract = MovieDetailView(layoutInflater, viewGroup)
}
