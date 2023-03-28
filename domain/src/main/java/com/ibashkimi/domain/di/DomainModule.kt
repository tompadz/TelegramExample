package com.ibashkimi.domain.di

import com.ibashkimi.data.repository.AuthRepository
import com.ibashkimi.domain.usecase.AuthUseCase
import com.ibashkimi.domain.usecase_impl.AuthUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideAuthUseCase(authRepository : AuthRepository): AuthUseCase {
        return AuthUseCaseImpl(authRepository)
    }
}