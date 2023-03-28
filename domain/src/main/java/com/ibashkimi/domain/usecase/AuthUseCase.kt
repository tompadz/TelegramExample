package com.ibashkimi.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.drinkless.td.libcore.telegram.TdApi

interface AuthUseCase {
    suspend fun getAuthorizationState(): Flow<TdApi.AuthorizationState>
}