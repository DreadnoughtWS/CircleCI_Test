package com.academy.alfagiftmini.presentation.authentication.fragment.register

import android.os.Parcelable
import com.academy.alfagiftmini.domain.register.AlamatDataDomain
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegistrationDataModel(
    val fName: String,
    val lName: String,
    val email: String,
    val pass: String,
    val phoneNumber: String,
    val alamat: List<AlamatDataModel>
) : Parcelable {
    companion object {
        fun transforms(model: List<AlamatDataModel>): List<AlamatDataDomain> {
            return model.map {
                transform(it)
            }
        }

        fun transform(model: AlamatDataModel): AlamatDataDomain{
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
    }
}

@Parcelize
data class AlamatDataModel(
    val namaAlamat: String,
    val desc: String,
    val kelurahan: String,
    val kecamatan: String,
    val kota: String,
    val provinsi: String,
    val kodePos: String,
    val active: Int
) : Parcelable