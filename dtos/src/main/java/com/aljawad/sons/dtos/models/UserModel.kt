package com.aljawad.sons.dtos.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.recyclerview.widget.DiffUtil
import com.aljawad.sons.dtos.enums.GenderEnum
import com.aljawad.sons.dtos.enums.StatusEnum

data class UserModel(
    var id: Int? = null,
    var name: String? = "",
    var email: String? = "",
    var gender: String? = GenderEnum.MALE.gender,
    var status: String? = "",
) : BaseObservable() {

    companion object {
        var diffUtil: DiffUtil.ItemCallback<UserModel> =
            object : DiffUtil.ItemCallback<UserModel>() {
                override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                    return oldItem == newItem
                }

            }
    }

    @Bindable
    fun isMale() = gender == GenderEnum.MALE.gender

    @Bindable
    fun isActive() = status == StatusEnum.ACTIVE.status

}