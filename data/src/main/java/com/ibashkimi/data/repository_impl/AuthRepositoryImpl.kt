package com.ibashkimi.data.repository_impl

import com.ibashkimi.data.sdk.TelegramSDK
import com.ibashkimi.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import org.drinkless.td.libcore.telegram.TdApi
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val sdk : TelegramSDK) : AuthRepository {
    override suspend fun getAuthorizationState() : Flow<TdApi.AuthorizationState> {
        return sdk.send(TdApi.GetAuthorizationState(), TdApi.AuthorizationState::class.java)
    }
}