package com.academy.alfagiftmini.data.repository.local.riwayatpencarian.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.academy.alfagiftmini.domain.riwayatpencarian.model.RiwayatPencarianDomainModel

@Entity( tableName = "riwayat_pencarian_db")
data class RiwayatPencarianDataModel (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("text")
    val text: String
    ) {
    companion object {
        fun transformList(result: List<RiwayatPencarianDataModel>?): List<RiwayatPencarianDomainModel> {
            return result?.map {
                RiwayatPencarianDomainModel(it.text)
            } ?: listOf()
        }
    }
}