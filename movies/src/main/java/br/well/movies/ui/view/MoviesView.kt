package br.well.movies.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import br.well.coreapp.ext.gone
import br.well.coreapp.ext.showSnackBar
import br.well.coreapp.ext.visible
import br.well.coreapp.view.ObservableView
import br.well.moviedbservice.api.model.Movies
import br.well.movies.R
import br.well.movies.ui.view.adapter.MoviesAdapter
import br.well.movies.ui.view.adapter.model.MovieItemAdapter
import br.well.movies.ui.view.controller.MoviesViewContract
import br.well.movies.ui.view.listener.PaginationScrolledListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movies.view.*

class MoviesView(inflater: LayoutInflater, parent: ViewGroup?) :
    ObservableView<MoviesViewContract.Listener>(inflater, parent, R.layout.fragment_movies)
    , MoviesViewContract, MoviesAdapter.Listener {

    var isLoading = false
    var isLastPage = false

    private val moviesAdapter by lazy {
        MoviesAdapter(arrayListOf(), this)
    }

    override fun showLoading() {
        rootView.loadingView.visible()
    }

    override fun bindMovies(movie: Movies) {
        val moviesItemAdapter = arrayListOf<MovieItemAdapter>()

        movie.results.forEach {
            val movieItemAdapter =
                MovieItemAdapter(it.id, it.title, it.poster_path, it.vote_average, it.release_date)

            moviesItemAdapter.add(movieItemAdapter)
        }

        moviesAdapter.addAll(moviesItemAdapter)
        with(rootView.moviesView) {
            setHasFixedSize(true)
            if (adapter == null) {
                adapter = moviesAdapter
            }
            visible()

            addOnScrollListener(object : PaginationScrolledListener(layoutManager!!) {
                override fun isLoading(): Boolean {
                    return isLoading
                }

                override fun loadMoreItems() {
                    isLoading = true
                    listeners.forEach {
                        it.loadNextPage()
                    }
                }

                override fun isLastPage(): Boolean {
                    return isLastPage
                }

            })
        }
    }

    override fun hideLoading() {
        rootView.loadingView.gone()
    }

    override fun showMessageError(message: String, function: () -> Unit) {
        rootView.showSnackBar(message, Snackbar.LENGTH_SHORT, "Tentar novamente", function)
    }

    override fun showListLoad() {
        isLoading = true
        moviesAdapter.showLoading()
    }

    override fun hideListLoad() {
        isLoading = false
        moviesAdapter.hideLoading()
    }

    override fun onMovieClicked(movieId: Int) {
        listeners.forEach {
            it.onMovieClicked(movieId)
        }
    }
}