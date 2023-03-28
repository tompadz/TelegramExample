package com.ibashkimi.telegram.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibashkimi.data.utils.getName
import com.ibashkimi.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val useCase : AuthUseCase
) : ViewModel() {
    fun get() {
        viewModelScope.launch {
            useCase.getAuthorizationState().collect {
                Log.e("TestViewModel", it.getName())
            }
        }
    }
}