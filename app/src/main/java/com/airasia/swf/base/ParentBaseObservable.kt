package com.airasia.swf.base

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.airasia.swf.BR
import com.airasia.swf.utils.BindableDelegates

open class ParentBaseObservable : BaseObservable() {

    @get:Bindable
    var loadingProgress: Boolean by BindableDelegates(false, BR.loadingProgress)
}