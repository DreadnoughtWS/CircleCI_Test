package com.academy.alfagiftmini.presentation.authentication.viewmodel.login

import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginDataModel
import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginResponseModel
import com.academy.alfagiftmini.data.repository.network.register.model.RegisterDataModel
import com.academy.alfagiftmini.domain.loginlogout.LoginDataDomain
import com.academy.alfagiftmini.domain.loginlogout.LoginResponseDomain
import com.academy.alfagiftmini.domain.register.RegisterDataDomain

object LoginDummyData {
    fun initValidLoginData(): LoginDataDomain {
        return LoginDataDomain(
            "test", "tests"
        )
    }
    fun initEmptyEmailLoginData(): LoginDataDomain {
        return LoginDataDomain(
            "", "tests"
        )
    }
    fun initEmptyPassLoginData(): LoginDataDomain {
        return LoginDataDomain(
            "test", ""
        )
    }
    fun initRegData(): RegisterDataModel {
        return RegisterDataModel(null, "", "", "", "", "",  "",  listOf())
    }
}