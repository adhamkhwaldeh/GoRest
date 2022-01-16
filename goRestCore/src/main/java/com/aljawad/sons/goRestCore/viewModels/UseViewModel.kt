package com.aljawad.sons.goRestCore.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aljawad.sons.business.CreateUserUseCase
import com.aljawad.sons.business.DeleteUserUseCase
import com.aljawad.sons.dtos.models.UserModel
import com.aljawad.sons.gorestrepository.paging.PagingParamConfig
import com.aljawad.sons.gorestrepository.repositories.UserPagingRepository
import com.aljawad.sons.mainlibrary.states.BaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UseViewModel @Inject constructor(
    private val userPagingRepository: UserPagingRepository,
    private val createUserUseCase: CreateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val handle: SavedStateHandle
) : com.aljawad.sons.mainlibrary.base.BaseViewModel() {

    //region users paging
    private lateinit var _usersFlow: Flow<PagingData<UserModel>>
    val usersFlow: Flow<PagingData<UserModel>>
        get() = _usersFlow

    var payload = handle.getLiveData(
        "payload",
        PagingParamConfig.initialOffset
    )

    private var _payloadPointer = payload.value!!
    var refreshWithFilter: MutableLiveData<Boolean> = MutableLiveData()

    fun getUserList() = launchPagingAsync({
        userPagingRepository.getUserPageList(_payloadPointer)
    }, { flow ->
        _usersFlow = flow
//            .map { pagingData: PagingData<UserModel> ->
//            pagingData
//                            .map { poKo ->
//                        poKo
//                        LocationModel.LocationData(location)
//                    }
//                    .insertSeparators<LocationModel.LocationData, LocationModel> { before, after ->
//                        when {
//                            before == null -> null
//                            after == null -> null
//                            else -> LocationModel.LocationSeparator("Separator: $before-$after")
//                        }
//                    }
//        }
            .cachedIn(viewModelScope)
    })
    //endregion

    //region create user
    private lateinit var _userCreateFlow: Flow<BaseState<UserModel>>
    val userCreateFlow: Flow<BaseState<UserModel>>
        get() = _userCreateFlow

    fun createUser(userModel: UserModel) { //: Flow<BaseState>
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO)
                .launch {
                    _userCreateFlow = createUserUseCase(userModel)//.map { poKo -> poKo }
                }
        }
    }
    //endregion

    //region delete user
    private lateinit var _userDeleteFlow: Flow<BaseState<Any?>>
    val userDeleteFlow: Flow<BaseState<Any?>>
        get() = _userDeleteFlow

    fun deleteUser(userId: Int) { //: Flow<BaseState>
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO)
                .launch {
                    _userDeleteFlow = deleteUserUseCase(userId).map { poKo -> poKo }
                }
        }
    }
    //endregion

}