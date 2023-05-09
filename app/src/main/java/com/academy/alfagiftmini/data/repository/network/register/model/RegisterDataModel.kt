package com.academy.alfagiftmini.data.repository.network.register.model

import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import com.google.gson.annotations.SerializedName

data class RegisterDataModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("phone")
    val phoneNumber: String,
    @SerializedName("memberId")
    val memberId: String
) {
    companion object {
        fun transform(user: RegisterDataModel?): RegisterDataDomain {
            return RegisterDataDomain(
                user?.id,
                user?.email ?: "",
                user?.password ?: "",
                user?.firstName ?: "",
                user?.lastName ?: "",
                user?.phoneNumber ?: "",
                user?.memberId ?: "")
        }

        fun transformToModel(user: RegisterDataDomain?): RegisterDataModel{
            return RegisterDataModel(
                user?.id,
                user?.email ?: "",
                user?.password ?: "",
                user?.firstName ?: "",
                user?.lastName ?: "",
                user?.phone ?: "",
                user?.memberId ?: ""
            )
        }
    }
}

data class RegisterResponseModel(
    @SerializedName("accessToken")
    val accessToken: String?,
    @SerializedName("user")
    val user: RegisterDataModel?,
    @SerializedName("error")
    val error: String?
)
