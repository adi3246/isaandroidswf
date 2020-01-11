package com.airasia.swf.module.schedule.viewModel

import com.airasia.swf.ApplicationClass
import com.airasia.swf.base.BaseViewModel
import com.airasia.swf.R
import com.airasia.swf.module.engineersList.model.EngineerModel
import com.airasia.swf.module.schedule.model.ScheduleListModel
import com.airasia.swf.module.schedule.model.Shift
import java.util.*
import kotlin.collections.ArrayList


class ScheduleViewModel: BaseViewModel()  {
    val scheduleForm = ScheduleForm()
    private var engineersListModel = ArrayList<EngineerModel>()
    private var availableEngineers = ArrayList<EngineerModel>()
    private val scheduleList: ArrayList<ScheduleListModel> = ArrayList()

    fun generateSchedule(){
        if (engineersListModel.size>0){
            availableEngineers = engineersListModel.clone() as ArrayList<EngineerModel>

            for (i in 0..9) {
                scheduleList.add(i, ScheduleListModel())
                for (j in 0..1) {
                    scheduleList[i].shifts.add(makeShift())
                    removeSelectedEngineer(scheduleList[i].shifts[j])
                }
            }

            scheduleForm.scheduleList = scheduleList
        }else{
            statusMessage.value = ApplicationClass.contextApp.getString(R.string.no_engineer_available)
        }
    }

    fun setEngineersData(engineersListModel: ArrayList<EngineerModel>){
        this.engineersListModel = engineersListModel
    }

    private fun makeShift(): Shift{

        if (availableEngineers.size==0){
            availableEngineers = engineersListModel.clone() as ArrayList<EngineerModel>
        }

        var staff: EngineerModel

        if (scheduleList.size >= 2){
            if (scheduleList[scheduleList.size - 2].shifts.size == 2){
                var ok: Boolean
                do{
                    var same_id1 = true
                    var same_id2 = true
                    staff = availableEngineers[Random().nextInt(availableEngineers.size)]

                    if (scheduleList[scheduleList.size - 2].shifts[0].id != staff.id)
                        same_id1 = false

                    if (scheduleList[scheduleList.size - 2].shifts[1].id != staff.id)
                        same_id2 = false

                    ok = same_id1 || same_id2
                }while (ok)
            }else{
                staff = availableEngineers[Random().nextInt(availableEngineers.size)]
            }
        }else{
            staff = availableEngineers[Random().nextInt(availableEngineers.size)]
        }

        return Shift(staff.id, staff.name)
    }

    private fun removeSelectedEngineer(shift: Shift){
        for (value in availableEngineers) {
            if (value.id == shift.id){
                availableEngineers.remove(value)
                break
            }
        }
    }
}