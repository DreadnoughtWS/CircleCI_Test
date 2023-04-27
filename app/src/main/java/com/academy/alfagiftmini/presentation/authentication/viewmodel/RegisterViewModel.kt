package com.academy.alfagiftmini.presentation.authentication.viewmodel

import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.domain.register.RegisterDomainUseCase
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val useCase: RegisterDomainUseCase): ViewModel() {
}