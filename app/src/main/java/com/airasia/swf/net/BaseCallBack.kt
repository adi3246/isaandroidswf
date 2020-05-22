package com.airasia.swf.net

import com.airasia.swf.ApplicationClass.Companion.contextApp
import com.airasia.swf.genericResponse.BaseResponse
import my.airo.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseCallback<T>(private val listener: OnCallback) : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        when {
            response.isSuccessful -> when {
                response.body() is ArrayList<*> -> listener.status(true, (response.body() as Any))
                (response.body() as? BaseResponse)?.error.isNullOrBlank() -> listener.status(true, (response.body() as Any))
                else -> listener.status(false, (response.body() as? BaseResponse)!!.error!!)
            }
            else -> {
                listener.status(false, contextApp.getString(R.string.try_again))
            }
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        listener.status(false, t.message!!)
    }

    interface OnCallback {
        fun status(success: Boolean, data: Any)
    }
}
