package com.academy.alfagiftmini.presentation.akun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityEditAkunBinding
import com.academy.alfagiftmini.databinding.ActivityRegisterBinding
import com.academy.alfagiftmini.domain.akun.AkunResponseDomain
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.AkunViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.MainActivityViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditAkunActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditAkunBinding
    private var data: AkunResponseDomain? = null
    @Inject
    lateinit var viewModelFactory: PresentationFactory
    private val akunViewModel: AkunViewModel by viewModels {
        viewModelFactory
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.akunActivityInject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityEditAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUI()
    }

    private fun setUI() {
        lifecycleScope.launch {
            val id = intent.getIntExtra("ID", 0)
            akunViewModel.getAkunData(id).collectLatest {
                data = it
            }
        }.invokeOnCompletion {
            binding.apply {
                etFirstNameEdit.setText(data?.userData?.firstName.toString())
                etLastNameEdit.setText(data?.userData?.lastName.toString())
                etPhoneNumberEdit.setText(data?.userData?.phoneNumber.toString())
                etEmailEdit.setText(data?.userData?.email.toString())
            }
        }
    }
}