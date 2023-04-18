package com.academy.alfagiftmini.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCase
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import com.academy.alfagiftmini.presentation.authentication.viewmodel.LoginViewModel
import com.academy.alfagiftmini.presentation.homepage.viewmodel.OfficialStoreViewModel
import javax.inject.Inject

class PresentationFactory @Inject constructor(
    private var loginUseCase: LoginDomainUseCase,
    private var officialStoreUseCase: OfficialStoreDomainUseCase,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(
                loginUseCase
            ) as T
            modelClass.isAssignableFrom(OfficialStoreViewModel::class.java) -> OfficialStoreViewModel(
                officialStoreUseCase
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

}