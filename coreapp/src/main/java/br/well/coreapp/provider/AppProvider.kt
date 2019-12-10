package br.well.coreapp.provider

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import br.well.coreapp.factory.UseCaseFactory
import br.well.coreapp.factory.ViewFactory
import br.well.coreapp.view.base.BaseAppProvider

class AppProvider(override val activity: AppCompatActivity, @IdRes frameId: Int) :
        BaseAppProvider(activity) {
    val viewFactory by lazy { ViewFactory(layoutInflater) }
    val screenNavigator by lazy {
        ScreenNavigator(
                activity
        )
    }
    val useCaseFactory by lazy {
        UseCaseFactory(
                SchedulerProvider
        )
    }
}