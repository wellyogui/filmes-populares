package br.well.coreapp.view

import br.well.coreapp.view.base.BaseController
import br.well.coreapp.view.base.BaseListener


abstract class LiveController<VIEW_LISTENER : BaseListener, VIEW_CONTRACT : ObservableViewContract<VIEW_LISTENER>>
    : BaseController<VIEW_CONTRACT>() {
    abstract fun observeLive()

    override fun onCreate(view: VIEW_CONTRACT) {
        super.onCreate(view)
        observeLive()
    }
}