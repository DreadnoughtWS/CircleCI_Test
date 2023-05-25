package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.R
import com.academy.alfagiftmini.databinding.ActivityEditAkunBinding
import com.academy.alfagiftmini.domain.akun.AkunDomainEditDataModel
import com.academy.alfagiftmini.domain.akun.AkunDomainUseCase
import com.academy.alfagiftmini.domain.akun.AkunResponseDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AkunViewModel @Inject constructor(private val useCase: AkunDomainUseCase): ViewModel() {
    fun getAkunData(id: Int): Flow<AkunResponseDomain> {
        return useCase.getAkunDetail(id)
    }

    suspend fun updateAkunData(editedAkunData: AkunDomainEditDataModel, id: Int){
        withContext(Dispatchers.IO){
            useCase.updateAkun(editedAkunData, id)
        }
    }

    fun checkEmpty(context: Context, binding: ActivityEditAkunBinding): Boolean {
        var firstNameValid = true
        var lastNameValid = true
        if (binding.etFirstNameEdit.text.isNullOrBlank()) {
            setError(binding.tvFirstNameError, context.getString(R.string.empty_field_error))
            firstNameValid = false
        }else {
            setGone(binding.tvFirstNameError)
        }

        if (binding.etLastNameEdit.text.isNullOrBlank()) {
            setError(binding.tvLastNameError, context.getString(R.string.empty_field_error))
            lastNameValid = false
        }else{
            setGone(binding.tvLastNameError)
        }
        return lastNameValid && firstNameValid
    }

    private fun setGone(textView: TextView) {
        textView.visibility = View.INVISIBLE
    }

    private fun setError(textView: TextView, msg: String) {
        textView.visibility = View.VISIBLE
        textView.text = msg
    }
}