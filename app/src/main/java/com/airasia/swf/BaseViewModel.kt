package com.airasia.swf

import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {

    val statusMessage = SingleLiveEvent<String>()

}