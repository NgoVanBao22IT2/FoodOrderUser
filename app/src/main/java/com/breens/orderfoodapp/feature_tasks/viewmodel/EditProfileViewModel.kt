package com.breens.orderfoodapp.feature_tasks.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.breens.orderfoodapp.data.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch



class EditProfileViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(UserProfile(avatar = "https://img.freepik.com/premium-photo/people-generating-images-using-artificial-intelligence-laptop_23-2150794312.jpg?w=1480"))
    val uiState: StateFlow<UserProfile> = _uiState.asStateFlow()

    fun updateFullName(fullName: String) {
        _uiState.update { currentState ->
            currentState.copy(fullName = fullName)
        }
    }

    fun updateGender(gender: Int) {
        _uiState.update { currentState ->
            currentState.copy(gender = gender)
        }
    }

    fun updateEmail(email: String) {
        _uiState.update { currentState ->
            currentState.copy(email = email)
        }
    }

    fun updatePhoneNumber(phoneNumber: String) {
        _uiState.update { currentState ->
            currentState.copy(phoneNumber = phoneNumber)
        }
    }



    fun updateDateOfBirth(dateOfBirth: Long) {
        _uiState.update { currentState ->
            currentState.copy(dateOfBirth = dateOfBirth)
        }
    }

    fun updateAvatar(avatar: String) {
        _uiState.update { currentState ->
            currentState.copy(avatar = avatar)
        }
    }

}