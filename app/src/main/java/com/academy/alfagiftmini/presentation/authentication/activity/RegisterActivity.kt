package com.academy.alfagiftmini.presentation.authentication.activity

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityRegisterBinding
import com.academy.alfagiftmini.presentation.authentication.viewmodel.RegisterViewModel
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {
    @Inject
    lateinit var presentationFactory: PresentationFactory
    private val registerViewModel: RegisterViewModel by viewModels {
        presentationFactory
    }
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.registerActivityInject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun getModel(): RegisterViewModel {
        return registerViewModel
    }

    fun getApp(): Application {
        return application
    }
}