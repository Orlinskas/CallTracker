package com.sandiplus.calltracker.ui.fragment

import com.sandiplus.calltracker.core.BaseFragment
import com.sandiplus.calltracker.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment() {

    override val layoutResId: Int
        get() = TODO("Not yet implemented")

    private val viewModel = getViewModel<MainViewModel>()

    // or

    private val viewModelObtained: MainViewModel by lazy {
        obtainViewModel(requireActivity(), MainViewModel::class.java)
    }

}