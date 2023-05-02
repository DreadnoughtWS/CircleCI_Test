package com.academy.alfagiftmini.presentation.authentication.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.domain.register.RegisterDomainUseCase
import javax.inject.Inject
import kotlin.random.Random

class RegisterViewModel @Inject constructor(private val useCase: RegisterDomainUseCase): ViewModel() {

    private var _otp: String = "999999"
    var otp = _otp
    fun generateOTP() {
        val generatedOTP = Random.nextInt(0,999999)
        _otp = String.format("%06d", generatedOTP)
        otp = _otp
    }

    private val _timer: MutableLiveData<Int> = MutableLiveData(0)
    private val _finished: MutableLiveData<Boolean> = MutableLiveData(false)
    var timer:LiveData<Int> = _timer
    var finished:LiveData<Boolean> = _finished
    fun otpCountdownTimer(){
        _finished.value = false
        object : CountDownTimer(301000, 1000) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                val time = millisUntilFinished / 1000
                _timer.value = time.toInt()
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {
                _finished.value = true
            }
        }.start()
    }
}