package com.airasia.swf.module.engineersList.viewModel

import com.airasia.swf.base.BaseViewModel
import com.airasia.swf.module.engineersList.model.EngineerModel
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