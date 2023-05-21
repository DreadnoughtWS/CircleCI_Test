package com.academy.alfagiftmini.data.repository.network.akun

import com.academy.alfagiftmini.domain.akun.AkunDomainDataModel
import com.academy.alfagiftmini.domain.akun.AkunDomainEditDataModel
import com.academy.alfagiftmini.domain.akun.AlamatAkunDataDomain
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
    val memberId: String,
    @SerializedName("address")
    val alamat: List<AlamatDataAkunModel>
){
    companion object {

        fun transformToModel(user: AkunDataModel?): AkunDomainDataModel {
            return AkunDomainDataModel(
                user?.id,
                user?.email ?: "",
                user?.password ?: "",
                user?.firstName ?: "",
                user?.lastName ?: "",
                user?.phoneNumber ?: "",
                user?.memberId ?: "",
                transformAlamatToDomain(user?.alamat ?: listOf())
            )
        }

        private fun transformAlamatToDomain(model: List<AlamatDataAkunModel>): List<AlamatAkunDataDomain>{
            return model.map {
                transformAlamatModel(it)
            }
        }

        private fun transformAlamatModel(model: AlamatDataAkunModel): AlamatAkunDataDomain {
            return AlamatAkunDataDomain(
                model.namaAlamat,
                model.desc,
                model.kelurahan,
                model.kecamatan,
                model.kota,
                model.provinsi,
                model.kodePos,
                model.active
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

data class AlamatDataAkunModel(
    @SerializedName("nama_alamat")
    val namaAlamat: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("kel")
    val kelurahan: String,
    @SerializedName("kec")
    val kecamatan: String,
    @SerializedName("kab/kot")
    val kota: String,
    @SerializedName("prov")
    val provinsi: String,
    @SerializedName("kode_pos")
    val kodePos: String,
    @SerializedName("active")
    val active: Int
)