package com.academy.alfagiftmini.data.repository.network.loginlogout.model

import com.google.gson.annotations.SerializedName

data class LoginDataModel(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
