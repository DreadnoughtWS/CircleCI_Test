package com.academy.alfagiftmini.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.academy.alfagiftmini.data.repository.netwok.loginlogout.LoginDomainUseCase
import com.academy.alfagiftmini.presentation.authentication.viewmodel.LoginViewModel
import javax.inject.Inject

class PresentationFactory @Inject constructor(
    private var loginUseCase: LoginDomainUseCase,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(
                loginUseCase
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

}