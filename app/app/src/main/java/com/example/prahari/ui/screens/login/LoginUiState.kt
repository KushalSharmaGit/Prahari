package com.example.prahari.ui.screens.login


data class LoginUiState(

    val email: String = "",

    val password: String = "",

    val isLoading: Boolean = false,

    val error: String? = null

)