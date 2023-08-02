package com.academy.alfagiftmini.presentation.authentication.viewmodel

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityLoginBinding
import com.academy.alfagiftmini.domain.loginlogout.LoginDataDomain
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCase
import com.academy.alfagiftmini.domain.loginlogout.LoginResponseDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val useCase: LoginDomainUseCase) : ViewModel() {
    fun checkUserInputValidity (user: LoginDataDomain): Pair<String, Boolean> {
        if (user.email.isBlank()) {
            return Pair("email", false)
        }
        if (user.password.isBlank()) {
            return Pair("pass", false)
        }
        return Pair("email pass", true)
    }

    fun login(user: LoginDataDomain): Flow<LoginResponseDomain> {
        return useCase.login(user)
    }
}