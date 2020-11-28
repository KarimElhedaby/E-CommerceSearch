package com.example.e_commerce_search.utils

import android.app.Activity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.e_commerce_search.R
import kotlinx.android.synthetic.main.toast_layout.view.*


object MyToast {
    private fun show(context: Activity, msg: String, isLong: Boolean, state: Int = 0) {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(
            R.layout.toast_layout,
            context.findViewById<ViewGroup>(R.id.toast_root)
        )
        val image = view.toast_image
        val text = view.toast_text

        /*
        * 0 -> success
        * 1 -> warning
        * 2 -> error
        * */

        when (state) {
            0 -> {
                view.background =
                    ContextCompat.getDrawable(context, R.drawable.toast_success_backgrond)
                text.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
            }
            1 -> {
                view.background =
                    ContextCompat.getDrawable(context, R.drawable.toast_warning_backgrond)
            }
            2 -> {
                view.background =
                    ContextCompat.getDrawable(context, R.drawable.toast_error_backgrond)
            }
        }

        //bind data to toast
        image.visibility = View.GONE
        text.text = msg

        val toast = Toast(context)
        toast.setGravity(Gravity.BOTTOM, 0, 100)
        toast.duration = if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT

        toast.view = view
        toast.show()
    }

    fun showSuccess(context: Activity, msg: String, isLong: Boolean = false) {
        show(context, msg, isLong)
    }

    fun showWarning(context: Activity, msg: String, isLong: Boolean = false) {
        show(context, msg, isLong, 1)
    }

    fun showError(context: Activity, msg: String, isLong: Boolean = false) {
        show(context, msg, isLong, 2)
    }
}