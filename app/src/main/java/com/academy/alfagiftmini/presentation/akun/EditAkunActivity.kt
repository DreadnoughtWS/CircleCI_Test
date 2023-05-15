package com.academy.alfagiftmini.presentation.akun

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.databinding.ActivityEditAkunBinding
import com.academy.alfagiftmini.domain.akun.AkunDomainDataModel
import com.academy.alfagiftmini.domain.akun.AkunDomainEditDataModel
import com.academy.alfagiftmini.domain.akun.AkunResponseDomain
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.PresentationUtils.SP_FIRST_NAME
import com.academy.alfagiftmini.presentation.PresentationUtils.SP_LAST_NAME
import com.academy.alfagiftmini.presentation.PresentationUtils.SP_PASS
import com.academy.alfagiftmini.presentation.PresentationUtils.SP_PHONE
import com.academy.alfagiftmini.presentation.factory.PresentationFactory
import com.academy.alfagiftmini.presentation.homepage.activity.MainActivity
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.AkunViewModel
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
    private var id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.akunActivityInject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityEditAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id = intent.getIntExtra("ID", 0)
        setUI()
    }

    private fun setUI() {
        lifecycleScope.launch {
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
        binding.apply {
            btnProfileBack.setOnClickListener {
                finish()
            }
            btnSubmitUserData.setOnClickListener {
                val sharedPreference = getSharedPreferences(
                    PresentationUtils.SHARED_PREFERENCE,
                    Context.MODE_PRIVATE
                )
                //check internet

                //update data
                lifecycleScope.launch {
                    akunViewModel.updateAkunData(
                        AkunDomainEditDataModel(
                            etFirstNameEdit.text.toString(),
                            etLastNameEdit.text.toString()
                        ), id
                    )
                }.invokeOnCompletion {
                    sharedPreference.edit().apply {
                        putString(SP_FIRST_NAME, etFirstNameEdit.text.toString())
                        putString(SP_LAST_NAME, etLastNameEdit.text.toString())
                    }.apply()
                }
            }
        }
    }
}