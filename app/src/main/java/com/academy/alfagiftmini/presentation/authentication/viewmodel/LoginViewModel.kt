package com.academy.alfagiftmini.presentation.authentication.viewmodel

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityLoginBinding
import com.academy.alfagiftmini.domain.loginlogout.LoginDataDomain
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCase
import com.academy.alfagiftmini.domain.loginlogout.LoginResponseDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val useCase: LoginDomainUseCase) : ViewModel() {
    fun checkUserInputValidity (context: Context, binding: ActivityLoginBinding, user: LoginDataDomain): Boolean {
        binding.apply {
            if (user.email.isBlank()) {
                setError(tvEmailErr, context.getString(R.string.empty_field_error))
                return false
            }
            setGone(tvEmailErr)
            if (user.password.isBlank()) {
                setError(tvPassErr, context.getString(R.string.empty_field_error))
                return false
            }
            setGone(tvPassErr)
        }
        return true
    }

    private fun setGone(textView: TextView) {
        textView.visibility = View.GONE
    }

    fun setBackendError(binding: ActivityLoginBinding, msg: String) {
        binding.apply {
            if (msg.contains("password", ignoreCase = true)) setError(tvPassErr, msg) else setError(tvEmailErr, msg)
        }
    }

    private fun setError(textView: TextView, msg: String) {
        textView.visibility = View.VISIBLE
        textView.text = msg
    }

    fun login(user: LoginDataDomain): Flow<LoginResponseDomain> {
        return useCase.login(user)
    }
}