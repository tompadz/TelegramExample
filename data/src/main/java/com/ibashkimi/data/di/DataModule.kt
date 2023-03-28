package com.ibashkimi.data.di

import android.content.Context
import android.os.Build
import com.ibashkimi.data.R
import com.ibashkimi.data.sdk.TelegramSDK
import com.ibashkimi.data.repository.AuthRepository
import com.ibashkimi.data.repository_impl.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.drinkless.td.libcore.telegram.TdApi
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAuthRepository(sdk : TelegramSDK): AuthRepository {
        return AuthRepositoryImpl(sdk)
    }

    @Provides
    @Singleton
    fun provideTelegramSDK() : TelegramSDK {
        return TelegramSDK()
    }

    @Provides
    fun provideTdlibParameters(@ApplicationContext context: Context): TdApi.TdlibParameters {
        return TdApi.TdlibParameters().apply {
            apiId = context.resources.getInteger(R.integer.telegram_api_id)
            apiHash = context.getString(R.string.telegram_api_hash)
            useMessageDatabase = true
            systemLanguageCode = Locale.getDefault().language
            databaseDirectory = context.filesDir.absolutePath
            deviceModel = Build.MODEL
            systemVersion = Build.VERSION.RELEASE
            applicationVersion = "0.1"
            enableStorageOptimizer = true
        }
    }
}