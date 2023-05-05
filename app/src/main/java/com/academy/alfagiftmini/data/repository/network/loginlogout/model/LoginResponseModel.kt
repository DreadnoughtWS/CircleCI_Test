package com.academy.alfagiftmini.data.repository.network.loginlogout.model

import com.academy.alfagiftmini.data.repository.network.register.model.RegisterDataModel
import com.academy.alfagiftmini.domain.loginlogout.LoginResponseDomain
import com.google.gson.annotations.SerializedName

data class LoginResponseModel (
    @SerializedName("accessToken")
    val accessToken: String?,
    @SerializedName("user")
    val user: RegisterDataModel?,
    @SerializedName("error")
    val error: String?
) {
    companion object {
        fun transform(loginResponseModel: LoginResponseModel): LoginResponseDomain {
            return LoginResponseDomain(
                loginResponseModel.accessToken ?: "",
                RegisterDataModel.transform(loginResponseModel.user ?: RegisterDataModel(-1, "", "", "", "", "", "")),
                loginResponseModel.error ?: ""
            )
        }
    }
}