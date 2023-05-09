package com.academy.alfagiftmini.presentation.authentication.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.domain.loginlogout.LoginDataDomain
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCase
import com.academy.alfagiftmini.domain.loginlogout.LoginResponseDomain
import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import com.academy.alfagiftmini.presentation.PresentationUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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