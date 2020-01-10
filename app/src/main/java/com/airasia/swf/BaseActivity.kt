package com.airasia.swf

import android.Manifest
import android.content.DialogInterface
import android.content.IntentSender
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.text.format.DateFormat
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

open class BaseActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showAlertDialog(message: String, listenerAlertDialog: OnAlertDialogActionListener?){
        AlertDialog.Builder(this)
            .setTitle("")
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("ok") { _: DialogInterface, i: Int ->
                listenerAlertDialog?.onOkClick()
            }.show()
    }

    fun hideBackButton(){
        supportActionBar!!.setHomeButtonEnabled(false)    // Disable the button
        supportActionBar!!.setDisplayHomeAsUpEnabled(false) // Remove the left caret
        supportActionBar!!.setDisplayShowHomeEnabled(false)
    }

    fun dipToPixels(dipValue:Float):Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, Resources.getSystem().displayMetrics)
    }


    fun getBitmapFromImageView(view: ImageView): Bitmap {
        return (view.drawable as BitmapDrawable).bitmap
    }

    interface OnAlertDialogActionListener {
        fun onOkClick()
    }
}