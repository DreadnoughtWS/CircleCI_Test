package com.academy.alfagiftmini.presentation.authentication.viewmodel

import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.domain.loginlogout.LoginDataDomain
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCase
import com.academy.alfagiftmini.domain.loginlogout.LoginResponseDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val useCase: LoginDomainUseCase) : ViewModel() {

    fun checkUserInputValidity (user: LoginDataDomain): Flow<LoginResponseDomain>  {
        //TODO(check)
        return login(user)
    }

    private fun login(user: LoginDataDomain): Flow<LoginResponseDomain> {
        return useCase.login(user)
    }
}