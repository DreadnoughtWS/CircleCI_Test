package com.academy.alfagiftmini.presentation.authentication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
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
        if (getAccessToken().isNotBlank()) {
            toMainActivity()
        }
        setViewInteraction()
    }

    private fun getAccessToken(): String {
        return mainViewModel.getAccessToken(this)
    }

    private fun setViewInteraction() {
        binding.apply {
            btnSubmitUserData.setOnClickListener{
                checkInternet(it)
            }
            btnRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
                finish()
            }
        }
    }

    private fun checkInternet(it: View) {
        if (PresentationUtils.isNetworkAvailable(this@LoginActivity)) {
            getUserData()
        }else{
            hideKeyboard(it)
            networkDialog(it)
        }
    }

    private fun networkDialog(view: View) {
        val dialogBuilder = AlertDialog.Builder(this, R.style.NetworkAlertDialogTheme)
        dialogBuilder.setMessage("No Network Connection detected, Please make sure you have a stable connection to the internet, then press retry to refresh the app and try again.")
        dialogBuilder.setCancelable(false)
        dialogBuilder.setIcon(R.drawable.no_internet_logo)
        dialogBuilder.setTitle("No Network Connection")
        dialogBuilder.setPositiveButton("RETRY") { _, _ ->
            checkInternet(view)
        }
        dialogBuilder.setNegativeButton("CLOSE") { dialog, _ ->
            dialog.cancel()
        }
        val connectionAlertDialog = dialogBuilder.create()
        connectionAlertDialog.window?.setBackgroundDrawableResource(R.drawable.connection_dialog_background)
        connectionAlertDialog.show()
    }

    private fun getInputManager(): InputMethodManager {
        return getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    }

    private fun hideKeyboard(view: View){
        val inputMethodManager = this.getInputManager()
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
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
                    mainViewModel.saveData(this@LoginActivity, it)
                    toMainActivity()
                }
            }
        }
    }

    private fun toMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}