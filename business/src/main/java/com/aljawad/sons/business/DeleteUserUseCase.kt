package com.aljawad.sons.business

import com.aljawad.sons.gorestrepository.repositories.UserRepository
import com.aljawad.sons.mainlibrary.states.BaseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private var repo: UserRepository
) : com.aljawad.sons.mainlibrary.base.BaseFlowUseCase<Void, Int>() {

    override suspend fun run(params: Int): Flow<BaseState<Void>> {
        return repo.deleteUser(params)
    }

}