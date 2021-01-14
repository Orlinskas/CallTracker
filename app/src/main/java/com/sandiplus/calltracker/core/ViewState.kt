package com.sandiplus.calltracker.core

import android.os.Bundle

interface ViewState {

    fun onRestoreState(bundle: Bundle?) {}

    fun onSaveState(bundle: Bundle?) {}
}
