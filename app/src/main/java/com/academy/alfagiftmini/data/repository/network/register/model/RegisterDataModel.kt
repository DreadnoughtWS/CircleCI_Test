package com.academy.alfagiftmini.data.repository.network.register.model

import com.academy.alfagiftmini.domain.register.AlamatDataDomain
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
    val memberId: String,
    @SerializedName("address")
    val alamat: List<AlamatDataRegistrationModel>
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
                user?.memberId ?: "",
                transformAlamatToDomain(user?.alamat ?: listOf())
            )
        }

        private fun transformAlamatToDomain(model: List<AlamatDataRegistrationModel>): List<AlamatDataDomain>{
            return model.map {
                transformAlamatModel(it)
            }
        }

        private fun transformAlamatModel(model: AlamatDataRegistrationModel): AlamatDataDomain{
            return AlamatDataDomain(
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

        fun transformToModel(user: RegisterDataDomain?): RegisterDataModel{
            return RegisterDataModel(
                user?.id,
                user?.email ?: "",
                user?.password ?: "",
                user?.firstName ?: "",
                user?.lastName ?: "",
                user?.phone ?: "",
                user?.memberId ?: "",
                transformAlamatToModel(user?.alamat ?: listOf())
            )
        }

        private fun transformAlamatToModel(model: List<AlamatDataDomain>): List<AlamatDataRegistrationModel>{
            return model.map {
                transformAlamat(it)
            }
        }
        private fun transformAlamat(model: AlamatDataDomain): AlamatDataRegistrationModel{
            return AlamatDataRegistrationModel(
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

data class RegisterResponseModel(
    @SerializedName("accessToken")
    val accessToken: String?,
    @SerializedName("user")
    val user: RegisterDataModel?,
    @SerializedName("error")
    val error: String?
)

data class AlamatDataRegistrationModel(
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

