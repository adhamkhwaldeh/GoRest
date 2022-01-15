package com.aljawad.sons.dtos.response

data class PagingResponse(
    var total: Int,
    var pages: Int,
    var page: Int,
    var limit: Int
) {
}