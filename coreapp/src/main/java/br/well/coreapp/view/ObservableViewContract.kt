package br.well.coreapp.view

import br.well.coreapp.view.base.BaseListener

interface ObservableViewContract<LISTENER_TYPE : BaseListener> : RootViewContract {
    fun registerListener(listener: LISTENER_TYPE)
    fun unregisterListener(listener: LISTENER_TYPE)
}