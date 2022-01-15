package com.aljawad.sons.gorestrepository.repositories

import androidx.paging.PagingData
import com.aljawad.sons.dtos.models.UserModel
import kotlinx.coroutines.flow.Flow

interface UserPagingRepository {
    //    fun getUserPageList(payload: KMutableProperty0<Int>): Flow<PagingData<UserModel>>
    fun getUserPageList(payload: Int): Flow<PagingData<UserModel>>
}