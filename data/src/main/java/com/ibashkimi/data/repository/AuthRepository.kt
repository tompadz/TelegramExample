package com.ibashkimi.data.repository

import kotlinx.coroutines.flow.Flow
import org.drinkless.td.libcore.telegram.TdApi

interface AuthRepository {
    suspend fun getAuthorizationState(): Flow<TdApi.AuthorizationState>
}