package br.well.moviedetail.common.factory

import androidx.lifecycle.LifecycleOwner
import br.well.moviedetail.common.provider.AppProvider

class ControllerFactory(
        private val useCaseFactory: UseCaseFactory,
        private val appProvider: AppProvider,
        private val lifecycleOwner: LifecycleOwner
) {

}
