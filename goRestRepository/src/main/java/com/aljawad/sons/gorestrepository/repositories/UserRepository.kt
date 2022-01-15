package com.aljawad.sons.gorestrepository.repositories

import com.aljawad.sons.network.apiServices.apiInterfaces.UserApi
import com.aljawad.sons.dtos.models.UserModel
import com.aljawad.sons.mainlibrary.states.BaseState
import com.aljawad.sons.mainlibrary.states.requestBlockingById
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UserRepository @Inject constructor(
    private var apiService: UserApi
) {

    fun createUser(userModel: UserModel): Flow<BaseState<UserModel>> {
        return requestBlockingById { apiService.createUser(userModel) }
    }

    fun deleteUser(userId: Int): Flow<BaseState<Void>> {
        return requestBlockingById { apiService.deleteUser(userId) }
    }

}
