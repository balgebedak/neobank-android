package com.example.neobank.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    // Simulated user state
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _phone = MutableStateFlow("")
    val phone: StateFlow<String> = _phone

    fun login(email: String, password: String): Boolean {
        if (email.isNotBlank() && password.isNotBlank()) {
            _isAuthenticated.value = true
            _userName.value = "John Doe"
            _email.value = email
            _phone.value = "+93700123456"
            return true
        }
        return false
    }

    fun logout() {
        _isAuthenticated.value = false
        _userName.value = ""
        _email.value = ""
        _phone.value = ""
    }

    fun updateUserProfile(name: String) {
        _userName.value = name
        // TODO: Sync with backend
    }

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
        // TODO: Trigger verification email or backend update
    }

    fun updatePhone(newPhone: String) {
        _phone.value = newPhone
        // TODO: Trigger phone verification logic
    }

    fun changePassword(old: String, new: String): Boolean {
        // TODO: Validate with backend
        return old != new && new.length >= 6
    }
}
