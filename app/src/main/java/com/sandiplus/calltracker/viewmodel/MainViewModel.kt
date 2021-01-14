package com.sandiplus.calltracker.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import com.sandiplus.calltracker.core.BaseViewModel

class MainViewModel @ViewModelInject constructor(

) : BaseViewModel() {

    init {
        addSaveStateHandler()
    }

}