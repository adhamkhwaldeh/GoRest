package com.aljawad.sons.dtos.models

import androidx.recyclerview.widget.DiffUtil

data class UserModel(
    var id: Int?,
    var name: String?,
    var email: String?,
    var gender: String?,
    var status: String?,
) {

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

}