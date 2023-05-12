package com.academy.alfagiftmini.presentation.authentication.fragment.register

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegistrationDataModel(
    val fName: String,
    val lName: String,
    val email: String,
    val pass: String,
    val phoneNumber: String
) : Parcelable