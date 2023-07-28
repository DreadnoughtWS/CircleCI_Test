package com.academy.alfagiftmini.data.repository.repositoryimpl

import com.academy.alfagiftmini.data.repository.local.riwayatpencarian.RiwayatPencarianDao
import com.academy.alfagiftmini.data.repository.local.riwayatpencarian.model.RiwayatPencarianDataModel

class RiwayatPencarianDaoDummy: RiwayatPencarianDao {
    override fun insertPencarian(data: RiwayatPencarianDataModel) {
        TODO("Not yet implemented")
    }

    override fun getData(): List<RiwayatPencarianDataModel> {
        TODO("Not yet implemented")
    }

    override fun deletePencarian() {
        TODO("Not yet implemented")
    }

}