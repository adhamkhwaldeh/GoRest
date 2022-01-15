package com.aljawad.sons.dtos.response

open class BaseResponse<T> {

    var isOk: Boolean = true

//    var message: Message = Message()

    var data: T? = null

    var currentPage: Int = 0

    var totalPages: Int = 0

    fun getErrorsCollections(): String? {
        return ""
    }
}