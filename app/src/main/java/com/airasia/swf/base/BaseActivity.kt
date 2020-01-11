package com.airasia.swf.base

import android.content.DialogInterface
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.TypedValue
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

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