package br.well.moviedetail.common.provider

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import br.well.coreapp.SchedulerProvider
import br.well.coreapp.view.base.BaseAppProvider
import br.well.moviedetail.common.factory.UseCaseFactory
import br.well.moviedetail.common.factory.ViewFactory

class AppProvider(override val activity: AppCompatActivity, @IdRes frameId: Int) :
        BaseAppProvider(activity) {
    val viewFactory by lazy { ViewFactory(layoutInflater) }

    val useCaseFactory by lazy {
        UseCaseFactory(
            SchedulerProvider
        )
    }
}