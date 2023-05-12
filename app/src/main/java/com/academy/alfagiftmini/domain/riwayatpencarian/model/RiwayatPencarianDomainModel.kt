package com.academy.alfagiftmini.domain.riwayatpencarian.model

import com.academy.alfagiftmini.data.repository.local.riwayatpencarian.model.RiwayatPencarianDataModel

data class RiwayatPencarianDomainModel(
    val text: String
){
    companion object {
        fun transform(it: RiwayatPencarianDomainModel): RiwayatPencarianDataModel {
            return RiwayatPencarianDataModel(text = it.text)
        }
    }
}
