package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import android.app.Presentation
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.MyApplication
import com.academy.alfagiftmini.domain.loginlogout.LoginResponseDomain
import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import com.academy.alfagiftmini.presentation.PresentationUtils

class MainActivityViewModel: ViewModel() {
    private val _userData = MutableLiveData<RegisterDataDomain>()
    val _getUserData: LiveData<RegisterDataDomain> = _userData

    fun saveData(activity: AppCompatActivity, it: RegisterDataDomain) {
        val sharedPreference = activity.application.getSharedPreferences(PresentationUtils.SHARED_PREFERENCE, Context.MODE_PRIVATE)
        sharedPreference.edit().apply{
            putInt(PresentationUtils.SP_USER_ID, it.id ?: -1)
            putString(PresentationUtils.SP_FIRST_NAME, it.firstName)
            putString(PresentationUtils.SP_LAST_NAME, it.lastName)
            putString(PresentationUtils.SP_PHONE, it.phone)
        }.apply()
    }

    fun getData(application: MyApplication) {
        val sharedPreference = application.getSharedPreferences(PresentationUtils.SHARED_PREFERENCE, Context.MODE_PRIVATE)
        _userData.value = RegisterDataDomain(sharedPreference.getInt(PresentationUtils.SP_USER_ID, -1), "", "", sharedPreference.getString(PresentationUtils.SP_FIRST_NAME, ""), sharedPreference.getString(PresentationUtils.SP_LAST_NAME, ""), sharedPreference.getString(PresentationUtils.SP_PHONE, ""), sharedPreference.getString(PresentationUtils.SP_PHONE, ""))
    }
}