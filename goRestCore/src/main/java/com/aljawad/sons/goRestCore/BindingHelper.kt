package com.aljawad.sons.goRestCore

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.aljawad.sons.dtos.enums.GenderEnum
import com.aljawad.sons.dtos.enums.StatusEnum

object BindingHelper {

    @JvmStatic
    @BindingAdapter("userGender")
    fun userGender(imageView: ImageView?, gender: String?) {
        try {
            imageView?.let {
                gender?.let { innerIt ->
                    when (innerIt) {
                        GenderEnum.MALE.gender -> {
                            it.setImageResource(android.R.drawable.ic_menu_sort_alphabetically)
                        }
                        GenderEnum.FEMALE.gender -> {
                            it.setImageResource(android.R.drawable.presence_offline)
                        }
                        else -> {

                        }
                    }
                }
            }
        } catch (ex: Exception) {

        }
    }

    @JvmStatic
    @BindingAdapter("userStatus")
    fun userStatus(view: View?, status: String?) {
        try {
            view?.let {
                val context = it.context
                status?.let { innerIt ->
                    when (innerIt) {
                        StatusEnum.ACTIVE.status -> {
                            it.setBackgroundResource(android.R.drawable.presence_online)
                        }
                        StatusEnum.INACTIVE.status -> {
                            it.setBackgroundResource(android.R.drawable.presence_offline)
                        }
                        else -> {
                            it.setBackgroundColor(
                                ContextCompat.getColor(
                                    context,
                                    android.R.color.transparent
                                )
                            )
                        }
                    }
                }
            }
        } catch (ex: Exception) {

        }
    }
}