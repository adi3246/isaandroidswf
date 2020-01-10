package com.airasia.swf.module.engineersList.viewModel


import androidx.databinding.Bindable
import com.airasia.swf.BR
import com.airasia.swf.ParentBaseObservable
import com.airasia.swf.module.engineersList.model.EngineersListModel
import com.airasia.swf.utils.BindableDelegates
import java.util.ArrayList

class EngineersListForm : ParentBaseObservable() {
    @get:Bindable
    var engineersList: ArrayList<EngineersListModel> by BindableDelegates(ArrayList(), BR.engineersList)

}