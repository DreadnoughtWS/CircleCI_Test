package com.academy.alfagiftmini.data.repository.network.akun

import com.academy.alfagiftmini.domain.akun.AkunDomainDataModel
import com.academy.alfagiftmini.domain.akun.AkunDomainEditDataModel
import com.google.gson.annotations.SerializedName

data class AkunDataModel(
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
){
    companion object {
        fun transform(user: AkunDataModel?): AkunDomainDataModel {
            return AkunDomainDataModel(
                user?.id,
                user?.email ?: "",
                user?.password ?: "",
                user?.firstName ?: "",
                user?.lastName ?: "",
                user?.phoneNumber ?: "",
                user?.memberId ?: "")
        }

        fun transformToModel(user: AkunDataModel?): AkunDomainDataModel {
            return AkunDomainDataModel(
                user?.id,
                user?.email ?: "",
                user?.password ?: "",
                user?.firstName ?: "",
                user?.lastName ?: "",
                user?.phoneNumber ?: "",
                user?.memberId ?: ""
            )
        }
    }
}

data class AkunEditModel(
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
){
    companion object{
        fun transform(editData: AkunDomainEditDataModel): AkunEditModel{
            return AkunEditModel(
                editData.firstName,
                editData.lastName
            )
        }
    }
}

data class AkunResponseModel(
    @SerializedName("accessToken")
    val accessToken: String?,
    @SerializedName("user")
    val userData: AkunDataModel?,
    @SerializedName("error")
    val error: String?
)