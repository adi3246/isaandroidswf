package com.airasia.swf.postsList

import android.util.Log
import com.airasia.swf.BaseViewModel
import com.airasia.swf.net.Api
import com.airasia.swf.net.BaseCallback


class PostListViewModel: BaseViewModel()  {

    val notificationForm = NotificationForm()

    var notificationId = 0

    var offset = 0

    var notificationTempModels= ArrayList<NotificationModel>()

    val updateNotificationListAdapter = SingleLiveEvent<Boolean>()

    var loadMoreInProgress = false

    fun clearData(){
        offset = 0
        notificationTempModels = ArrayList()
        notificationForm.notificationList = ArrayList()
    }

    fun getNotification(isRefresh: Boolean, isLoadMore: Boolean){
        if (isRefresh){
            notificationForm.hasMoreData = false
        }else{
            if (!isLoadMore){
                notificationForm.loadingProgress = true
            }
        }

        loadMoreInProgress = true

        Api.getApi()?.notification(offset)!!.enqueue(
            BaseCallback(object :
                BaseCallback.OnCallback {
                override fun status(success: Boolean, data: Any) {
                    notificationForm.loadingProgress = false
                    if (success) {
                        loadMoreInProgress = false

                        val notificationResponseList = data as NotificationResponse
                        notificationTempModels = ArrayList()

                        for (item in notificationResponseList.data)
                            notificationTempModels.add(NotificationModel(item))

                        statusMessage.value = ""

                        if (notificationForm.notificationList.size == 0) {
                            notificationForm.notificationList = notificationTempModels
                            if (notificationForm.notificationList.size > 9) notificationForm.hasMoreData = true
                        } else {
                            for (item in notificationTempModels)
                                notificationForm.notificationList.add(item)

                            updateNotificationListAdapter.value = true
                        }

                        offset++

                        if (notificationTempModels.size < 10) notificationForm.hasMoreData = false
                    } else
                        statusMessage.value = data as String
                }
            })
        )
    }

    fun readNotification(id: Int){

        Api.getApi()?.notificationRead(id)!!.enqueue(
            BaseCallback(object :
                BaseCallback.OnCallback {
                override fun status(success: Boolean, data: Any) {
                    Log.e("notif: ", success.toString())
                }
            })
        )
    }
}