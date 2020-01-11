package com.airasia.swf.base

import androidx.lifecycle.ViewModel
import com.airasia.swf.SingleLiveEvent

open class BaseViewModel: ViewModel() {

    val statusMessage = SingleLiveEvent<String>()

}