package com.airasia.swf.module.schedule.viewModel

import androidx.databinding.Bindable
import com.airasia.swf.BR
import com.airasia.swf.ParentBaseObservable
import com.airasia.swf.module.engineersList.model.EngineersListModel
import com.airasia.swf.module.schedule.model.ScheduleListModel
import com.airasia.swf.utils.BindableDelegates
import java.util.ArrayList


class ScheduleForm : ParentBaseObservable() {
    @get:Bindable
    var scheduleList: ArrayList<ScheduleListModel> by BindableDelegates(ArrayList(), BR.scheduleList)

}