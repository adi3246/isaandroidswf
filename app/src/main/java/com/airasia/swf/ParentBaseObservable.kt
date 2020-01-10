package com.airasia.swf

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.airasia.swf.utils.BindableDelegates

open class ParentBaseObservable : BaseObservable() {

    @get:Bindable
    var loadingProgress: Boolean by BindableDelegates(false, BR.loadingProgress)
}