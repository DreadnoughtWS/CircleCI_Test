package com.academy.alfagiftmini.presentation.authentication.viewmodel

import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCase
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val useCase: LoginDomainUseCase) : ViewModel() {
}