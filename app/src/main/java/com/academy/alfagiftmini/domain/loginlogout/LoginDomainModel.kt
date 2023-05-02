package com.academy.alfagiftmini.domain.loginlogout

import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginDataModel
import com.academy.alfagiftmini.domain.register.RegisterDataDomain

data class LoginDataDomain (
        val email: String,
        val password: String
) {
        companion object {
                fun transform(user: LoginDataDomain): LoginDataModel {
                        return LoginDataModel(user.email, user.password)
                }
        }
}

data class LoginResponseDomain (
        val accessToken: String,
        val user: RegisterDataDomain,
        val error: String
        )