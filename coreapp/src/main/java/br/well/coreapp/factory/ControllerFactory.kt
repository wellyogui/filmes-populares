package br.well.coreapp.factory

import androidx.lifecycle.LifecycleOwner
import br.well.coreapp.provider.AppProvider

class ControllerFactory(
        private val useCaseFactory: UseCaseFactory,
        private val appProvider: AppProvider,
        private val lifecycleOwner: LifecycleOwner
)
