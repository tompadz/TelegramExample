package com.ibashkimi.domain.usecase_impl

import com.ibashkimi.data.repository.AuthRepository
import com.ibashkimi.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.Flow
import org.drinkless.td.libcore.telegram.TdApi
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) :
    AuthUseCase {
    override suspend fun getAuthorizationState() : Flow<TdApi.AuthorizationState> {
        return authRepository.getAuthorizationState()
    }
}