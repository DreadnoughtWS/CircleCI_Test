package com.academy.alfagiftmini.presentation.homepage.viewmodel

import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCase
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import javax.inject.Inject

class OfficialStoreViewModel @Inject constructor(private val useCase: OfficialStoreDomainUseCase) : ViewModel() {
}