package com.academy.alfagiftmini.presentation.authentication.viewmodel

import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentInputUserDataBinding
import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import com.academy.alfagiftmini.domain.register.RegisterDomainUseCase
import com.academy.alfagiftmini.domain.register.RegisterResponseDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.random.Random

class RegisterViewModel @Inject constructor(private val useCase: RegisterDomainUseCase) :
    ViewModel() {
    //input data validation
    fun userDataValidate(binding: FragmentInputUserDataBinding): Boolean {
        var checkErr: Boolean
        binding.apply {
            val fName = etFirstName.text
            val lName = etLastName.text
            val email = etEmail.text
            val emailReg = "^[\\w-]+@([\\w-]+\\.)+[\\w-]{2,4}$".toRegex()
            val pass = etPassword.text
            val passConfirm = etPasswordConfirm.text
            //first name
            if (fName.isNullOrEmpty()){
                tvFnErr.visibility = View.VISIBLE
                tvFnErr.text = "Please fill out this field"
                etFirstName.setBackgroundResource(R.drawable.edit_text_error_border)
                checkErr = true
            }
            else {
                tvFnErr.visibility = View.INVISIBLE
                etFirstName.setBackgroundResource(R.drawable.custom_edit_text_rounded_corner)
                checkErr = false
            }

            //last name
            if (lName.isNullOrEmpty()){
                tvLnErr.visibility = View.VISIBLE
                tvLnErr.text = "Please fill out this field"
                etLastName.setBackgroundResource(R.drawable.edit_text_error_border)
                checkErr = true
            }
            else {
                tvLnErr.visibility = View.INVISIBLE
                etLastName.setBackgroundResource(R.drawable.custom_edit_text_rounded_corner)
                checkErr = false
            }

            //email
            if (email.isNullOrEmpty()){
                tvEmailErr.visibility = View.VISIBLE
                tvEmailErr.text = "Please fill out this field"
                etEmail.setBackgroundResource(R.drawable.edit_text_error_border)
                checkErr = true
            }
            else if (!email.contains(emailReg)){
                tvEmailErr.visibility = View.VISIBLE
                tvEmailErr.text = "please use the correct email address format"
                etEmail.setBackgroundResource(R.drawable.edit_text_error_border)
                checkErr = true
            }
            else {
                tvEmailErr.visibility = View.INVISIBLE
                etEmail.setBackgroundResource(R.drawable.custom_edit_text_rounded_corner)
                checkErr = false
            }

            //password
            if (pass.isNullOrEmpty()){
                tvPassErr.visibility = View.VISIBLE
                tvPassErr.text = "Please fill out this field"
                etPassword.setBackgroundResource(R.drawable.edit_text_error_border)
                checkErr = true
            }
            else if (pass.length !in 5..20){
                tvPassErr.visibility = View.VISIBLE
                tvPassErr.text = "Password length must be between 5 - 20"
                etPassword.setBackgroundResource(R.drawable.edit_text_error_border)
                checkErr = true
            }
            else {
                tvPassErr.visibility = View.INVISIBLE
                etPassword.setBackgroundResource(R.drawable.custom_edit_text_rounded_corner)
                checkErr = false
            }

            //confirmed password
            if (passConfirm.isNullOrEmpty()){
                tvPassConfirmErr.visibility = View.VISIBLE
                tvPassConfirmErr.text = "Please fill out this field"
                etPasswordConfirm.setBackgroundResource(R.drawable.edit_text_error_border)
                checkErr = true
            }
            else if (passConfirm.length !in 5..20){
                tvPassConfirmErr.visibility = View.VISIBLE
                tvPassConfirmErr.text = "Password length must be between 5 - 20"
                etPasswordConfirm.setBackgroundResource(R.drawable.edit_text_error_border)
                checkErr = true
            }
            else if (passConfirm.toString() != pass.toString()){
                tvPassConfirmErr.visibility = View.VISIBLE
                tvPassConfirmErr.text = "Password are not the same"
                Log.d("pass", pass.toString())
                Log.d("passConfirm", passConfirm.toString())
                etPasswordConfirm.setBackgroundResource(R.drawable.edit_text_error_border)
                checkErr = true
            }
            else {
                Log.d("pass2", pass.toString())
                Log.d("passConfirm2", passConfirm.toString())
                tvPassConfirmErr.visibility = View.INVISIBLE
                etPasswordConfirm.setBackgroundResource(R.drawable.custom_edit_text_rounded_corner)
                checkErr = false
            }
        }
        return checkErr
    }

    //check phone number length
    fun checkPhoneLength(phoneNumber: String): Boolean {
        return phoneNumber.length in 11..13
    }

    //reformat phone number
    fun phoneNumberFormatted(phoneNumber: String): String {
        return if (phoneNumber[0] == '0') {
            phoneNumber.replaceFirst("0", "+62")
        } else "+62".plus(phoneNumber)
    }

    //generate otp code
    private var _otp: String = "999999"
    var otp = _otp
    fun generateOTP() {
        val generatedOTP = Random.nextInt(0, 999999)
        _otp = String.format("%06d", generatedOTP)
        otp = _otp
    }

    //set timer for sending otp code again
    private val _timer: MutableLiveData<Int> = MutableLiveData(0)
    private val _finished: MutableLiveData<Boolean> = MutableLiveData(false)
    var timer: LiveData<Int> = _timer
    var finished: LiveData<Boolean> = _finished
    fun otpCountdownTimer() {
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

    fun register(newUser: RegisterDataDomain): Flow<RegisterResponseDomain> =
        useCase.register(newUser)
}