package com.airasia.swf.module.engineersList

import com.airasia.swf.base.BaseViewModel
import com.airasia.swf.module.engineersList.model.EngineerModel
import com.airasia.swf.module.engineersList.model.EngineersListForm
import com.airasia.swf.module.engineersList.response.EngineersListResponse
import com.airasia.swf.module.schedule.model.ScheduleListModel
import com.airasia.swf.net.Api
import com.airasia.swf.net.BaseCallback

/**
 * Created by Isa Andi on 08/01/2020.
 * <p>
 * EngineersListViewModel where the business logic for fetching list of engineers.
 *
 * @author Isa Andi
 * @version 1
 * @see EngineerModel
 * @see ScheduleListModel
 */
class EngineersListViewModel: BaseViewModel()  {

    val engineersListForm =
        EngineersListForm()

    fun fetchEngineersList(){

        engineersListForm.loadingProgress = true

        Api.getApi()?.getEngineers()!!.enqueue(
            BaseCallback(object :
                BaseCallback.OnCallback {
                override fun status(success: Boolean, data: Any) {
                    engineersListForm.loadingProgress = false
                    if (success) {
                        val engineersListResponse = data as EngineersListResponse
                        val engineers: ArrayList<EngineerModel> = ArrayList()

                        if (engineersListResponse.engineers != null){
                            for (item in engineersListResponse.engineers!!)
                                engineers.add(EngineerModel(item))
                        }

                        engineersListForm.engineersList = engineers
                    } else
                        statusMessage.value = data as String
                }
            })
        )
    }

    fun getEngineers(): ArrayList<EngineerModel> {
        return engineersListForm.engineersList
    }
}