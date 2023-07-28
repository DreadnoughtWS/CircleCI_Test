package com.academy.alfagiftmini.data.repository.repositoryimpl

import com.academy.alfagiftmini.data.repository.local.riwayatpencarian.model.RiwayatPencarianDataModel
import com.academy.alfagiftmini.domain.riwayatpencarian.model.RiwayatPencarianDomainModel

object RiwayatPencarianDummyDataProvider {
    fun provideDefaultData(): RiwayatPencarianDataModel {
        return RiwayatPencarianDataModel(
            0,
            "test"
        )
    }
    fun provideDataExampleOutput(): List<RiwayatPencarianDataModel> {
        return listOf(
            RiwayatPencarianDataModel(
                0,
                "test"
            ),
            RiwayatPencarianDataModel(
                1,
                "test1"
            )
        )
    }
}