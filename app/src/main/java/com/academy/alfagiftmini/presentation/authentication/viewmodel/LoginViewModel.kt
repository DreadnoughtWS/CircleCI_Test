package com.academy.alfagiftmini.presentation.authentication.viewmodel

import android.content.Intent
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
    private val _userData = MutableLiveData<RegisterDataDomain>()
    fun getUserLiveData(): LiveData<RegisterDataDomain> = _userData

    fun checkUserInputValidity (user: LoginDataDomain): Flow<LoginResponseDomain>  {
        //TODO(check)
        return login(user)
    }

    private fun getUserData(id: Int): Flow<RegisterDataDomain> {
        return useCase.getUserData(id)
    }

    fun getIntentData(intent: Intent) {
        CoroutineScope(IO).launch {
            getUserData(intent.getIntExtra(PresentationUtils.USER_ID_KEY, -1)).collectLatest {
                _userData.postValue(it)
            }
        }
    }

    private fun login(user: LoginDataDomain): Flow<LoginResponseDomain> {
        return useCase.login(user)
    }
}