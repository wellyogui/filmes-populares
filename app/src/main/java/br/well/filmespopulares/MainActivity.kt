package br.well.filmespopulares

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.well.coreapp.FragmentLayoutProvider
import br.well.coreapp.ScreenNavigator
import br.well.moviedbservice.api.MovieDbApi
import br.well.moviedetail.ui.view.controller.MovieDetailFragment
import br.well.movies.ui.view.controller.MoviesFragment

class MainActivity : AppCompatActivity(), FragmentLayoutProvider,
    ScreenNavigator {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initApi()
        openFragment(MoviesFragment.newInstance())
    }

    private fun initApi() {
        MovieDbApi.buildRetrofit()
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainRootView, fragment)
            .commitNow()
    }

    override fun fragmentFrame(): Int = R.id.mainRootView

    override fun toMovieDetail(movieId: Int) {
        openFragment(MovieDetailFragment.newInstance(movieId))
    }
}
