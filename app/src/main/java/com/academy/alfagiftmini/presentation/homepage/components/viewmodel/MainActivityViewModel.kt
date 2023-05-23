package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import android.app.Application
import android.app.Presentation
import android.content.Context
import android.content.SharedPreferences
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

    fun saveData(activity: AppCompatActivity, it: LoginResponseDomain) {
        val user = it.user
        val sharedPreference = getSharedPrefereces(activity.application as MyApplication)
        sharedPreference.edit().apply{
            putString(PresentationUtils.SP_ACC_TOKEN, it.accessToken)
            putInt(PresentationUtils.SP_USER_ID, user.id ?: -1)
            putString(PresentationUtils.SP_FIRST_NAME, user.firstName)
            putString(PresentationUtils.SP_LAST_NAME, user.lastName)
            putString(PresentationUtils.SP_PHONE, user.phone)
        }.apply()
    }

    private fun getSharedPrefereces(application: MyApplication): SharedPreferences {
        return application.getSharedPreferences(PresentationUtils.SHARED_PREFERENCE, Context.MODE_PRIVATE)
    }

    fun getAccessToken(activity: AppCompatActivity): String {
        val sharedPreference = getSharedPrefereces(activity.application as MyApplication)
        return sharedPreference.getString(PresentationUtils.SP_ACC_TOKEN, "") ?: ""
    }

    fun getData(application: MyApplication) {
        val sharedPreference = getSharedPrefereces(application)
        _userData.value = RegisterDataDomain(sharedPreference.getInt(PresentationUtils.SP_USER_ID, -1), "", "", sharedPreference.getString(PresentationUtils.SP_FIRST_NAME, ""), sharedPreference.getString(PresentationUtils.SP_LAST_NAME, ""), sharedPreference.getString(PresentationUtils.SP_PHONE, ""), sharedPreference.getString(PresentationUtils.SP_PHONE, ""), listOf())
    }
}