package com.academy.alfagiftmini.presentation.authentication.viewmodel

import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.domain.register.RegisterDomainUseCase
import javax.inject.Inject
import kotlin.random.Random

class RegisterViewModel @Inject constructor(private val useCase: RegisterDomainUseCase): ViewModel() {

    private var _otp: String = "999999"
    var otp = _otp
    fun generateOTP() {
        val otp = Random.nextInt(0,999999).toString()
        _otp = String().format("%06d", otp)
    }
}