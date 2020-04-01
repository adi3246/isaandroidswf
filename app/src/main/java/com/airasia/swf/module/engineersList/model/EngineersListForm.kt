package com.airasia.swf.module.engineersList.model


import androidx.databinding.Bindable
import com.airasia.swf.BR
import com.airasia.swf.base.ParentBaseObservable
import com.airasia.swf.module.engineersList.model.EngineerModel
import com.airasia.swf.utils.BindableDelegates
import java.util.ArrayList

class EngineersListForm : ParentBaseObservable() {
    @get:Bindable
    var engineersList: ArrayList<EngineerModel> by BindableDelegates(ArrayList(), BR.engineersList)

}