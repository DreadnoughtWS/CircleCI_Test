package com.academy.alfagiftmini.data.repository.network.register

import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginDataModel
import com.academy.alfagiftmini.domain.loginlogout.LoginDataDomain
import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import com.google.gson.annotations.SerializedName

data class RegisterDataModel(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("phone_number")
    val phonenumber: String,
    @SerializedName("member_id")
    val memberId: String
) {
    companion object {
        fun transform(user: RegisterDataModel?): RegisterDataDomain {
            return RegisterDataDomain(
                user?.email ?: "",
                user?.password ?: "",
                user?.firstName ?: "",
                user?.lastName ?: "",
                user?.phonenumber ?: "",
                user?.memberId ?: "")
        }
    }
}
