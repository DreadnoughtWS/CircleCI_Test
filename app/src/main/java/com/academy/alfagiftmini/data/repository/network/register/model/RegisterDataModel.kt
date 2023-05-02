package com.academy.alfagiftmini.data.repository.network.register.model

import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import com.google.gson.annotations.SerializedName

data class RegisterDataModel(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("phone")
    val phonenumber: String,
    @SerializedName("memberId")
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
