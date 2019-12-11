package br.well.filmespopulares

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.well.moviedbservice.api.MovieDbApi
import br.well.movies.common.provider.FragmentLayoutProvider
import br.well.movies.ui.view.controller.MoviesFragment

class MainActivity : AppCompatActivity(), FragmentLayoutProvider {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initApi()
        openFragment()
    }

    private fun initApi() {
        MovieDbApi.buildRetrofit()
    }

    private fun openFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainRootView, MoviesFragment.newInstance())
            .commitNow()
    }

    override fun fragmentFrame(): Int = R.id.mainRootView
}
