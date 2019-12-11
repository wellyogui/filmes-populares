package br.well.movies.common.factory

import android.view.LayoutInflater
import android.view.ViewGroup
import br.well.movies.ui.view.MoviesView
import br.well.movies.ui.view.controller.MoviesViewContract

class ViewFactory(private val layoutInflater: LayoutInflater) {
    fun provideMovieView(viewGroup: ViewGroup?): MoviesViewContract = MoviesView(layoutInflater, viewGroup)
}
