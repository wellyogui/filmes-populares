package br.well.movies.common.provider

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import br.well.coreapp.SchedulerProvider
import br.well.coreapp.view.base.BaseAppProvider
import br.well.movies.common.factory.UseCaseFactory
import br.well.movies.common.factory.ViewFactory

class AppProvider(override val activity: AppCompatActivity, @IdRes frameId: Int) :
        BaseAppProvider(activity) {
    val viewFactory by lazy { ViewFactory(layoutInflater) }

    val useCaseFactory by lazy {
        UseCaseFactory(
                SchedulerProvider
        )
    }
}