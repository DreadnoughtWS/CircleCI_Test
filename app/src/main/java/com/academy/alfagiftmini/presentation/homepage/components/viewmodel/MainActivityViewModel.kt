package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.domain.loginlogout.LoginResponseDomain
import com.academy.alfagiftmini.domain.register.RegisterDataDomain

class MainActivityViewModel: ViewModel() {
    private val _userData = MutableLiveData<RegisterDataDomain>()
    val _getUserData: LiveData<RegisterDataDomain> = _userData

    fun saveData(activity: AppCompatActivity, it: LoginResponseDomain) {
        val sharedPreference = activity.application.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        sharedPreference.edit().apply{
            putInt("USER_ID", it.user.id ?: -1)
            putString("FIRST_NAME", it.user.firstName)
            putString("LAST_NAME", it.user.lastName)
            putString("USER_PHONE", it.user.phone)
        }.apply()
    }

    fun getData(activity: AppCompatActivity) {
        val sharedPreference = activity.application.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)
        _userData.value = RegisterDataDomain(sharedPreference.getInt("USER_ID", -1), "", "", sharedPreference.getString("FIRST_NAME", ""), sharedPreference.getString("LAST_NAME", ""), "", sharedPreference.getString("USER_PHONE", ""))
    }
}