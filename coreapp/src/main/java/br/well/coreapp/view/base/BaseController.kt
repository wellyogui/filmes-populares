package br.well.coreapp.view.base

import androidx.annotation.CallSuper
import br.well.coreapp.view.RootViewContract

abstract class BaseController<VIEW_CONTRACT : RootViewContract> {
    lateinit var viewContract: VIEW_CONTRACT

    abstract fun initViews()

    abstract fun onStop()

    @CallSuper
    open fun onCreate(view: VIEW_CONTRACT) {
        this.viewContract = view
    }

    abstract fun onResume()
}