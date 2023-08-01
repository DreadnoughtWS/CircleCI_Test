package com.academy.alfagiftmini.data.repository.repositoryimpl

import com.academy.alfagiftmini.data.repository.local.riwayatpencarian.model.RiwayatPencarianDataModel
import com.academy.alfagiftmini.domain.riwayatpencarian.model.RiwayatPencarianDomainModel

object RiwayatPencarianDummy {
    fun initDefaultRiwayatPencarian(): RiwayatPencarianDomainModel {
        return RiwayatPencarianDomainModel(text = "")
    }

    fun initExpectedResponse(): List<RiwayatPencarianDomainModel> {
        return listOf(
            RiwayatPencarianDomainModel(text = "test"),
            RiwayatPencarianDomainModel(text = "test1")
        )
    }

    fun initDataModelExample(): List<RiwayatPencarianDataModel> {
        return listOf(
            RiwayatPencarianDataModel(id = 0, text= "test"),
            RiwayatPencarianDataModel(id= 1, text = "test1")
        )
    }
}