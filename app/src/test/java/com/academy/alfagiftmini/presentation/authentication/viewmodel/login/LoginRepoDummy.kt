package com.academy.alfagiftmini.presentation.authentication.viewmodel.login

import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginDataModel
import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginResponseModel
import com.academy.alfagiftmini.data.repository.network.register.model.RegisterDataModel
import com.academy.alfagiftmini.domain.loginlogout.LoginResponseDomain
import com.academy.alfagiftmini.domain.register.RegisterDataDomain

object LoginRepoDummy {
    fun initLoginResponse(token: String?, regModel: RegisterDataModel?, error: String?): LoginResponseModel {
        return LoginResponseModel(
            token,
            regModel,
            error
        )
    }

    fun initDefaultDomainLoginResp(): LoginResponseDomain {
        return LoginResponseDomain(
            "",
            RegisterDataDomain(-1,"", "", "", "", "", "",
                listOf()
            ),
            ""
        )
    }
    fun initDefaultDataLoginResp(): LoginResponseModel {
        return LoginResponseModel(
            "",
            RegisterDataModel(-1,"", "", "", "", "", "",
                listOf()
            ),
            ""
        )
    }

    fun initDefaultLoginDataModel(): LoginDataModel {
        return LoginDataModel(
            "test", "tests"
        )
    }
    fun initValidRegData(): RegisterDataModel {
        return RegisterDataModel(0, "da", "", "", "", "",  "",  listOf())
    }
    fun initDefaultRegData(): RegisterDataDomain {
        return RegisterDataDomain(0, "da", "", "", "", "",  "",  listOf())
    }

}