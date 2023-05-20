package com.academy.alfagiftmini.data.repository.local.riwayatpencarian

import androidx.room.*
import com.academy.alfagiftmini.data.repository.local.riwayatpencarian.model.RiwayatPencarianDataModel

@Dao
interface RiwayatPencarianDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPencarian(data: RiwayatPencarianDataModel)

    @Query("SELECT * FROM riwayat_pencarian_db ORDER BY id DESC")
    fun getData(): List<RiwayatPencarianDataModel>

    @Query("DELETE FROM riwayat_pencarian_db")
    fun deletePencarian()
}