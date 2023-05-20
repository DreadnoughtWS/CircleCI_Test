package com.academy.alfagiftmini.data.repository.local.riwayatpencarian.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.academy.alfagiftmini.domain.riwayatpencarian.model.RiwayatPencarianDomainModel

@Entity( tableName = "riwayat_pencarian_db", indices = [Index(value = ["text"], unique = true)])
data class RiwayatPencarianDataModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,
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