package com.academy.alfagiftmini.domain.akun

data class AkunDomainDataModel(
    val id: Int?,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val memberId: String,
    val alamat: List<AlamatAkunDataDomain>
)

data class AkunDomainEditDataModel(
    val firstName: String,
    val lastName: String,
)

data class AkunResponseDomain (
    val accessToken: String?,
    val userData: AkunDomainDataModel?,
    val error: String?
)

data class AlamatAkunDataDomain(
    val namaAlamat: String,
    val desc: String,
    val kelurahan: String,
    val kecamatan: String,
    val kota: String,
    val provinsi: String,
    val kodePos: String,
    val active: Int
)