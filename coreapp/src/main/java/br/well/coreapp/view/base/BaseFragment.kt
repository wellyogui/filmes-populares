package br.well.coreapp.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.well.coreapp.ScreenNavigator
import br.well.coreapp.view.RootViewContract

abstract class BaseFragment<VIEW_CONTRACT : RootViewContract, CONTROLLER : BaseController<VIEW_CONTRACT>,
        APP_PROVIDER : BaseAppProvider, CONTROLLER_FACTORY> : Fragment() {

    protected abstract val viewContract: VIEW_CONTRACT
    protected abstract val controller: CONTROLLER

    abstract val appProvider: APP_PROVIDER
    abstract val controllerFactory: CONTROLLER_FACTORY


    val listener: ScreenNavigator by lazy {
        if (activity is ScreenNavigator) {
            return@lazy activity as ScreenNavigator
        } else {
            throw NotImplementedError("You must implement SaveUserUseCase.")
        }
    }

    open fun initData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        controller.onCreate(viewContract)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return viewContract.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller.initViews()
    }

    fun backPressed(): Boolean {
        return if (childFragmentManager.backStackEntryCount > 0) {
            childFragmentManager.popBackStackImmediate()
        } else false
    }
}