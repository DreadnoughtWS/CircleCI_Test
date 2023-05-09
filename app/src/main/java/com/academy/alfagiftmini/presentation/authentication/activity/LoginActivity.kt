package com.academy.alfagiftmini.presentation.authentication.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.databinding.ActivityLoginBinding
import com.academy.alfagiftmini.domain.loginlogout.LoginDataDomain
import com.academy.alfagiftmini.presentation.PresentationUtils
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
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewInteraction()
    }

    private fun setViewInteraction() {
        binding.apply {
            btnSubmit.setOnClickListener{
                getUserData()
            }
            btnRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }

    }

    private fun getUserData() {
        binding.apply {
            lifecycleScope.launch {
                loginViewModel.checkUserInputValidity(LoginDataDomain(etEmail.text.toString(), etPassword.text.toString())).collectLatest {
                    if (it.error.isNotBlank()) {
                        Toast.makeText(this@LoginActivity, it.error, Toast.LENGTH_SHORT).show()
                        return@collectLatest
                    }
                    if (it.accessToken.isBlank()) return@collectLatest
                    //Toast.makeText(this@LoginActivity, it.user.firstName, Toast.LENGTH_SHORT).show()
                    mainViewModel.saveData(this@LoginActivity, it)
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}