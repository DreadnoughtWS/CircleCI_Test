package com.academy.alfagiftmini.presentation.authentication.viewmodel

import android.content.Context
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
import com.academy.alfagiftmini.presentation.PresentationUtils.COUNTRY_PHONE_CODE
import com.academy.alfagiftmini.presentation.PresentationUtils.EMAIL_REGEX
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
import kotlin.random.Random

class RegisterViewModel @Inject constructor(private val useCase: RegisterDomainUseCase) :
    ViewModel() {
    //post data to server
    fun registerNewUser(newUserData:RegisterDataDomain): Flow<RegisterResponseDomain>{
        return useCase.register(newUserData)
    }

    //input data validation
    fun userDataValidate(binding: FragmentInputUserDataBinding, context: Context): Boolean {
        var checkErr: Boolean
        binding.apply {
            val fName = etFirstName.text
            val lName = etLastName.text
            val email = etEmail.text
            val pass = etPassword.text.toString()
            val passConfirm = etPasswordConfirm.text.toString()
            //first name
            if (fName.isNullOrEmpty()){
                tvFnErr.visibility = View.VISIBLE
                tvFnErr.text = context.getString(R.string.empty_field_error)
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
                tvLnErr.text = context.getString(R.string.empty_field_error)
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
                tvEmailErr.text = context.getString(R.string.empty_field_error)
                etEmail.setBackgroundResource(R.drawable.edit_text_error_border)
                checkErr = true
            }
            else if (!email.contains(EMAIL_REGEX)){
                tvEmailErr.visibility = View.VISIBLE
                tvEmailErr.text = context.getString(R.string.email_format_error)
                etEmail.setBackgroundResource(R.drawable.edit_text_error_border)
                checkErr = true
            }
            else {
                tvEmailErr.visibility = View.INVISIBLE
                etEmail.setBackgroundResource(R.drawable.custom_edit_text_rounded_corner)
                checkErr = false
            }

            //password
            if (pass.isEmpty()){
                tvPassErr.visibility = View.VISIBLE
                tvPassErr.text = context.getString(R.string.empty_field_error)
                etPassword.setBackgroundResource(R.drawable.edit_text_error_border)
                checkErr = true
            }
            else if (pass.length !in 5..20){
                tvPassErr.visibility = View.VISIBLE
                tvPassErr.text = context.getString(R.string.password_length_error)
                etPassword.setBackgroundResource(R.drawable.edit_text_error_border)
                checkErr = true
            }
            else {
                tvPassErr.visibility = View.INVISIBLE
                etPassword.setBackgroundResource(R.drawable.custom_edit_text_rounded_corner)
                checkErr = false
            }

            //confirmed password
            if (passConfirm.isEmpty()){
                tvPassConfirmErr.visibility = View.VISIBLE
                tvPassConfirmErr.text = context.getString(R.string.empty_field_error)
                etPasswordConfirm.setBackgroundResource(R.drawable.edit_text_error_border)
                checkErr = true
            }
            else if (passConfirm.length !in 5..20){
                tvPassConfirmErr.visibility = View.VISIBLE
                tvPassConfirmErr.text = context.getString(R.string.password_length_error)
                etPasswordConfirm.setBackgroundResource(R.drawable.edit_text_error_border)
                checkErr = true
            }
            else if (passConfirm != pass){
                tvPassConfirmErr.visibility = View.VISIBLE
                tvPassConfirmErr.text = context.getString(R.string.confirm_password_error)
                Log.d("pass", pass)
                Log.d("passConfirm", passConfirm)
                etPasswordConfirm.setBackgroundResource(R.drawable.edit_text_error_border)
                checkErr = true
            }
            else {
                Log.d("pass2", pass)
                Log.d("passConfirm2", passConfirm)
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
            phoneNumber.replaceFirst("0", COUNTRY_PHONE_CODE)
        } else COUNTRY_PHONE_CODE.plus(phoneNumber)
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