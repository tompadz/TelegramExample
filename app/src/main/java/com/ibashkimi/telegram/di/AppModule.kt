package com.ibashkimi.telegram.di

import com.ibashkimi.data.di.DataModule
import com.ibashkimi.domain.di.DomainModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [DataModule::class, DomainModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

}