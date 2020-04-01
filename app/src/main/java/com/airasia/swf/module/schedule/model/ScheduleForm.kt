package com.airasia.swf.module.schedule.model

import androidx.databinding.Bindable
import com.airasia.swf.BR
import com.airasia.swf.base.ParentBaseObservable
import com.airasia.swf.module.schedule.model.ScheduleListModel
import com.airasia.swf.utils.BindableDelegates
import java.util.ArrayList


class ScheduleForm : ParentBaseObservable() {
    @get:Bindable
    var scheduleList: ArrayList<ScheduleListModel> by BindableDelegates(ArrayList(), BR.scheduleList)

}