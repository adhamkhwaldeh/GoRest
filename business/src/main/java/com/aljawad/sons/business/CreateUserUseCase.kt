package com.aljawad.sons.business

import com.aljawad.sons.dtos.models.UserModel
import com.aljawad.sons.gorestrepository.repositories.UserRepository
import com.aljawad.sons.mainlibrary.states.BaseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CreateUserUseCase @Inject constructor(
    private var repo: UserRepository
) : com.aljawad.sons.mainlibrary.base.BaseFlowUseCase<UserModel, UserModel>() {

    override suspend fun run(params: UserModel): Flow<BaseState<UserModel>> {
        return repo.createUser(params)
    }

}