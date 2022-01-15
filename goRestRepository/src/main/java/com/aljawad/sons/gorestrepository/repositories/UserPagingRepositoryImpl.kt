package com.aljawad.sons.gorestrepository.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aljawad.sons.network.apiServices.apiInterfaces.UserApi
import com.aljawad.sons.dtos.models.UserModel
import com.aljawad.sons.gorestrepository.paging.PagingParamConfig
import com.aljawad.sons.gorestrepository.paging.dataSource.UserPagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPagingRepositoryImpl @Inject constructor(private val service: UserApi) :
    UserPagingRepository {


    @ExperimentalPagingApi
    override fun getUserPageList(payload: Int): Flow<PagingData<UserModel>> = Pager(
        config = PagingConfig(
            pageSize = PagingParamConfig.pageSize,
            prefetchDistance = PagingParamConfig.prefetchDistance,
            enablePlaceholders = PagingParamConfig.enablePlaceholders,
            initialLoadSize = PagingParamConfig.initialLoadSize,
        ),
        pagingSourceFactory = { UserPagingDataSource(service, payload) },
        initialKey = payload,
        remoteMediator = null

    ).flow

}