package com.academy.alfagiftmini.presentation.authentication.viewmodel

import android.content.Context
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.FragmentInputUserDataBinding
import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import com.academy.alfagiftmini.domain.register.RegisterDomainUseCase
import com.academy.alfagiftmini.domain.register.RegisterResponseDomain
import com.academy.alfagiftmini.presentation.PresentationUtils.COUNTRY_PHONE_CODE
import com.academy.alfagiftmini.presentation.PresentationUtils.EMAIL_REGEX
import com.academy.alfagiftmini.presentation.PresentationUtils.SP_ACC_TOKEN
import com.academy.alfagiftmini.presentation.PresentationUtils.SP_FIRST_NAME
import com.academy.alfagiftmini.presentation.PresentationUtils.SP_LAST_NAME
import com.academy.alfagiftmini.presentation.PresentationUtils.SP_PHONE
import com.academy.alfagiftmini.presentation.PresentationUtils.SP_USER_ID
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import javax.inject.Inject
import kotlin.random.Random

class RegisterViewModel @Inject constructor(private val useCase: RegisterDomainUseCase) :
    ViewModel() {
    //input data
    private var _firstName: MutableLiveData<String> = MutableLiveData("")
    private var _lastName: MutableLiveData<String> = MutableLiveData("")
    private var _email: MutableLiveData<String> = MutableLiveData("")
    private var _phoneNumber: MutableLiveData<String> = MutableLiveData("")

    val firstName = _firstName
    val lastName = _lastName
    val eMail = _email
    val phoneNumber = _phoneNumber

    //put data to sharedPreference
    fun putDataToSharedPreference(activity: AppCompatActivity, it: RegisterResponseDomain) {
        val sharedPreference =
            activity.application.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        sharedPreference.edit().apply {
            putInt(SP_USER_ID, it.user?.id ?: -1)
            putString(SP_FIRST_NAME, it.user?.firstName)
            putString(SP_LAST_NAME, it.user?.lastName)
            putString(SP_PHONE, it.user?.phone)
            putString(SP_ACC_TOKEN, it.accessToken)
        }.apply()
    }

    //post data to server
    fun registerNewUser(newUserData: RegisterDataDomain): Flow<RegisterResponseDomain> {
        return useCase.register(newUserData)
    }

    //input data validation
    fun checkAvailableEmail(email: String): Flow<RegisterResponseDomain> {
        return useCase.checkAvailableEmail(email)
    }

    fun userDataValidate(
        binding: FragmentInputUserDataBinding,
        context: Context,
        check: RegisterResponseDomain?
    ): Boolean {
        var checkErr: Boolean
        binding.apply {
            val fName = etFirstName.text!!
            val lName = etLastName.text!!
            val email = etEmail.text!!
            val pass = etPassword.text.toString()
            val passConfirm = etPasswordConfirm.text.toString()

            val fNameCheck = checkFirstName(fName, context, this)
            val lNameCheck = checkLastName(lName, context, this)
            val emailCheck = checkEmail(email, context, this, check)
            val passCheck = checkPass(pass, context, this)
            val passConfirmCheck = checkConfirmPass(pass, passConfirm, context, this)

            checkErr = (fNameCheck || lNameCheck || emailCheck || passCheck || passConfirmCheck)
        }
        return checkErr
    }

    private fun checkFirstName(
        fName: Editable,
        context: Context,
        fragmentInputUserDataBinding: FragmentInputUserDataBinding
    ): Boolean {
        fragmentInputUserDataBinding.apply {
            return if (fName.isBlank()) {
                tvFnErr.visibility = View.VISIBLE
                tvFnErr.text = context.getString(R.string.empty_field_error)
                etFirstNameEditLayout.setBoxStrokeColorStateList(context.getColorStateList(R.color.edit_text_err))
                etFirstNameEditLayout.setBoxStrokeWidthResource(R.dimen.boxStrokeRadii)
                true
            } else {
                tvFnErr.visibility = View.INVISIBLE
                etFirstNameEditLayout.setBoxStrokeColorStateList(context.getColorStateList(R.color.edit_text_normal))
                etFirstNameEditLayout.setBoxStrokeWidthResource(R.dimen.boxStrokeRadiiNormal)
                _firstName.value = fName.toString()
                false
            }
        }
    }

    private fun checkLastName(
        lName: Editable,
        context: Context,
        fragmentInputUserDataBinding: FragmentInputUserDataBinding
    ): Boolean {
        fragmentInputUserDataBinding.apply {
            return if (lName.isBlank()) {
                tvLnErr.visibility = View.VISIBLE
                tvLnErr.text = context.getString(R.string.empty_field_error)
                etLastNameEditLayout.setBoxStrokeColorStateList(context.getColorStateList(R.color.edit_text_err))
                etLastNameEditLayout.setBoxStrokeWidthResource(R.dimen.boxStrokeRadii)
                true
            } else {
                tvLnErr.visibility = View.INVISIBLE
                etLastNameEditLayout.setBoxStrokeColorStateList(context.getColorStateList(R.color.edit_text_normal))
                etLastNameEditLayout.setBoxStrokeWidthResource(R.dimen.boxStrokeRadiiNormal)
                _lastName.value = lName.toString()
                false
            }
        }
    }

    private fun checkEmail(
        email: Editable,
        context: Context,
        fragmentInputUserDataBinding: FragmentInputUserDataBinding,
        check: RegisterResponseDomain?
    ): Boolean {
        fragmentInputUserDataBinding.apply {
            if (email.isBlank()) {
                tvEmailErr.visibility = View.VISIBLE
                tvEmailErr.text = context.getString(R.string.empty_field_error)
                etEmailEditLayout.setBoxStrokeColorStateList(context.getColorStateList(R.color.edit_text_err))
                etEmailEditLayout.setBoxStrokeWidthResource(R.dimen.boxStrokeRadii)
                return true
            } else if (!email.contains(EMAIL_REGEX)) {
                tvEmailErr.visibility = View.VISIBLE
                tvEmailErr.text = context.getString(R.string.email_format_error)
                etEmailEditLayout.setBoxStrokeColorStateList(context.getColorStateList(R.color.edit_text_err))
                etEmailEditLayout.setBoxStrokeWidthResource(R.dimen.boxStrokeRadii)
                return true
            } else if (!check?.user?.email.isNullOrBlank()) {
                tvEmailErr.visibility = View.VISIBLE
                tvEmailErr.text = context.getString(R.string.email_exists)
                etEmailEditLayout.setBoxStrokeColorStateList(context.getColorStateList(R.color.edit_text_err))
                etEmailEditLayout.setBoxStrokeWidthResource(R.dimen.boxStrokeRadii)
                return true
            } else {
                tvEmailErr.visibility = View.INVISIBLE
                etEmailEditLayout.setBoxStrokeColorStateList(context.getColorStateList(R.color.edit_text_normal))
                etEmailEditLayout.setBoxStrokeWidthResource(R.dimen.boxStrokeRadiiNormal)
                _email.value = email.toString()
                return false
            }
        }
    }

    private fun checkPass(
        pass: String,
        context: Context,
        fragmentInputUserDataBinding: FragmentInputUserDataBinding
    ): Boolean {
        fragmentInputUserDataBinding.apply {
            return if (pass.isBlank()) {
                tvPassErr.visibility = View.VISIBLE
                tvPassErr.text = context.getString(R.string.empty_field_error)
                etPasswordEditLayout.setBoxStrokeColorStateList(context.getColorStateList(R.color.edit_text_err))
                etPasswordEditLayout.setBoxStrokeWidthResource(R.dimen.boxStrokeRadii)
                true
            } else if (pass.length !in 5..20) {
                tvPassErr.visibility = View.VISIBLE
                tvPassErr.text = context.getString(R.string.password_length_error)
                etPasswordEditLayout.setBoxStrokeColorStateList(context.getColorStateList(R.color.edit_text_err))
                etPasswordEditLayout.setBoxStrokeWidthResource(R.dimen.boxStrokeRadii)
                true
            } else {
                tvPassErr.visibility = View.INVISIBLE
                etPasswordEditLayout.setBoxStrokeColorStateList(context.getColorStateList(R.color.edit_text_normal))
                etPasswordEditLayout.setBoxStrokeWidthResource(R.dimen.boxStrokeRadiiNormal)
                false
            }
        }
    }

    private fun checkConfirmPass(
        pass: String,
        passConfirm: String,
        context: Context,
        fragmentInputUserDataBinding: FragmentInputUserDataBinding
    ): Boolean {
        fragmentInputUserDataBinding.apply {
            if (passConfirm.isBlank()) {
                tvPassConfirmErr.visibility = View.VISIBLE
                tvPassConfirmErr.text = context.getString(R.string.empty_field_error)
                etPasswordConfirmEditLayout.setBoxStrokeColorStateList(context.getColorStateList(R.color.edit_text_err))
                etPasswordConfirmEditLayout.setBoxStrokeWidthResource(R.dimen.boxStrokeRadii)
                return true
            } else if (passConfirm.length !in 5..20) {
                tvPassConfirmErr.visibility = View.VISIBLE
                tvPassConfirmErr.text = context.getString(R.string.password_length_error)
                etPasswordConfirmEditLayout.setBoxStrokeColorStateList(context.getColorStateList(R.color.edit_text_err))
                etPasswordConfirmEditLayout.setBoxStrokeWidthResource(R.dimen.boxStrokeRadii)
                return true
            } else if (passConfirm != pass) {
                tvPassConfirmErr.visibility = View.VISIBLE
                tvPassConfirmErr.text = context.getString(R.string.confirm_password_error)
                Log.d("pass", pass)
                Log.d("passConfirm", passConfirm)
                etPasswordConfirmEditLayout.setBoxStrokeColorStateList(context.getColorStateList(R.color.edit_text_err))
                etPasswordConfirmEditLayout.setBoxStrokeWidthResource(R.dimen.boxStrokeRadii)
                return true
            } else {
                Log.d("pass2", pass)
                Log.d("passConfirm2", passConfirm)
                tvPassConfirmErr.visibility = View.INVISIBLE
                etPasswordConfirmEditLayout.setBoxStrokeColorStateList(context.getColorStateList(R.color.edit_text_normal))
                etPasswordConfirmEditLayout.setBoxStrokeWidthResource(R.dimen.boxStrokeRadiiNormal)
                return false
            }
        }
    }

    //check phone number length
    fun checkPhoneLength(phoneNumber: String): Boolean {
        return phoneNumber.length in 11..13
    }

    //reformat phone number
    fun phoneNumberFormatted(phoneNumber: String): String {
        _phoneNumber.value = phoneNumber
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

    //    fun otpCountdownTimer() {
//        Log.d("RegisterViewModel", "otpCountdownTimer() called")
//        _finished.value = false
//        val countDownTimer = object : CountDownTimer(301000, 1000) {
//            // Callback function, fired on regular interval
//            override fun onTick(millisUntilFinished: Long) {
//                val time = millisUntilFinished / 1000
//                _timer.value = time.toInt()
//                Log.d("RegisterViewModel", "onTick() - timer value: ${_timer.value}")
//            }
//
//            // Callback function, fired
//            // when the time is up
//            override fun onFinish() {
//                _finished.value = true
//                _timer.value = 0 // Reset the timer to 0
//                Log.d("RegisterViewModel", "onFinish() - finished value: ${_finished.value}")
//            }
//        }
//        countDownTimer.start()
//    }
    fun otpCountdownTimer() {
        _finished.postValue(false)
        for (i in 10 downTo 0) {
            Thread.sleep(1000)
            if (i == 0) {
                _finished.postValue(true)
            }
        }
    }
}