package com.academy.alfagiftmini.presentation.authentication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.databinding.ActivityLoginBinding
import com.academy.alfagiftmini.domain.loginlogout.LoginDataDomain
import com.academy.alfagiftmini.presentation.authentication.viewmodel.LoginViewModel
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.MainActivityViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val loginViewModel: LoginViewModel by viewModels {
        presentationFactory
    }
    private val mainViewModel: MainActivityViewModel by viewModels {
        presentationFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.loginActivityInject(this)
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewInteraction()
    }

    private fun setViewInteraction() {
        binding.apply {
            btnSubmitUserData.setOnClickListener{
                getUserData()
                finish()
            }
            btnRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
                finish()
            }
        }

    }

    private fun getUserData() {
        binding.apply {
            if (!loginViewModel.checkUserInputValidity(this@LoginActivity, binding, LoginDataDomain(etEmail.text.toString(), etPassword.text.toString()))) { return }
            lifecycleScope.launch {
                loginViewModel.login(LoginDataDomain(etEmail.text.toString(), etPassword.text.toString())).collectLatest {
                    if (it.error.isNotBlank()) {
                        loginViewModel.setBackendError(binding, it.error)
                        return@collectLatest
                    }
                    if (it.accessToken.isBlank()) return@collectLatest
                    mainViewModel.saveData(this@LoginActivity, it.user)
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}