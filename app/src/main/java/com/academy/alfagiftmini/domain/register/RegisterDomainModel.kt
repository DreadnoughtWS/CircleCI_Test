package com.academy.alfagiftmini.domain.register

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterDataDomain(
    val id: Int?,
    val email: String?,
    val password: String?,
    val firstName: String?,
    val lastName: String?,
    val phone: String?,
    val memberId: String?,
    val alamat: List<AlamatDataDomain>?
): Parcelable {
    fun getFullName(): String {
        return "$firstName $lastName"
    }
}

data class RegisterResponseDomain (
    val accessToken: String?,
    val user: RegisterDataDomain?,
    val error: String?
)

@Parcelize
data class AlamatDataDomain(
    val namaAlamat: String,
    val desc: String,
    val kelurahan: String,
    val kecamatan: String,
    val kota: String,
    val provinsi: String,
    val kodePos: String,
    val active: Int
) : Parcelable