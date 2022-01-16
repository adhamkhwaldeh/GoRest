package com.aljawad.sons.mainlibrary.base

import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment : DialogFragment() {


    override fun onResume() {
        super.onResume()
        if (dialog != null && dialog!!.window != null) {
            dialog!!.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )


            // this is to show dialog at the bottom of the screen
            val window: Window? = dialog!!.window
            val wlp: WindowManager.LayoutParams = window!!.getAttributes()
            wlp.gravity = Gravity.BOTTOM
//        wlp.width = WindowManager.LayoutParams.MATCH_PARENT
            window.setAttributes(wlp)
        }
    }
}