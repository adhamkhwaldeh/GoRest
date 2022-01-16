package com.aljawad.sons.goRestCore

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import com.aljawad.sons.dtos.enums.GenderEnum
import com.aljawad.sons.dtos.enums.StatusEnum
import com.aljawad.sons.mainlibrary.R

object BindingHelper {

    @JvmStatic
    @BindingAdapter("userGender")
    fun userGender(imageView: ImageView?, gender: String?) {
        try {
            imageView?.let {
                val context = it.context
                gender?.let { innerIt ->
                    when (innerIt) {
                        GenderEnum.MALE.gender -> {
//                            it.setColorFilter(
//                                ContextCompat.getColor(
//                                    context,
//                                    R.color.male
//                                ), android.graphics.PorterDuff.Mode.MULTIPLY
//                            )
                            it.setImageResource(R.drawable.ic_male)
                            DrawableCompat.setTint(
                                DrawableCompat.wrap(it.drawable),
                                ContextCompat.getColor(context, R.color.male)
                            )
                        }
                        GenderEnum.FEMALE.gender -> {
//                            it.setColorFilter(
//                                ContextCompat.getColor(
//                                    context,
//                                    R.color.female
//                                ), android.graphics.PorterDuff.Mode.MULTIPLY
//                            )
                            it.setImageResource(R.drawable.ic_female)
                            DrawableCompat.setTint(
                                DrawableCompat.wrap(it.drawable),
                                ContextCompat.getColor(context, R.color.female)
                            )

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