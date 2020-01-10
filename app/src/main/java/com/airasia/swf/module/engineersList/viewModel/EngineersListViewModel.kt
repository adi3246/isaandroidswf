package com.airasia.swf.module.engineersList.viewModel

import android.util.Log
import com.airasia.swf.BaseViewModel
import com.airasia.swf.SingleLiveEvent
import com.airasia.swf.module.engineersList.model.EngineersListModel
import com.airasia.swf.module.engineersList.response.EngineersListResponse
import com.airasia.swf.net.Api
import com.airasia.swf.net.BaseCallback


class EngineersListViewModel: BaseViewModel()  {

    val engineersListForm = EngineersListForm()

    fun fetchEngineersList(){

        engineersListForm.loadingProgress = true

        Api.getApi()?.getEngineers()!!.enqueue(
            BaseCallback(object :
                BaseCallback.OnCallback {
                override fun status(success: Boolean, data: Any) {
                    engineersListForm.loadingProgress = false
                    if (success) {
                        val engineersListResponse = data as EngineersListResponse
                        val engineers: ArrayList<EngineersListModel> = ArrayList()

                        if (engineersListResponse.engineers != null){
                            for (item in engineersListResponse.engineers!!)
                                engineers.add(EngineersListModel(item))
                        }

                        engineersListForm.engineersList = engineers
                    } else
                        statusMessage.value = data as String
                }
            })
        )
    }

    fun getEngineers(): ArrayList<EngineersListModel> {
        return engineersListForm.engineersList
    }
}