package com.aljawad.sons.dtos.response

import com.aljawad.sons.dtos.models.UserModel

data class UserListResponse(val meta: MetaPagingResponse, val data: MutableList<UserModel>)
