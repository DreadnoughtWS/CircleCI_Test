package com.academy.alfagiftmini.data.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.academy.alfagiftmini.data.repository.local.riwayatpencarian.RiwayatPencarianDao
import com.academy.alfagiftmini.data.repository.local.riwayatpencarian.model.RiwayatPencarianDataModel
import dagger.Module
import dagger.Provides

@Module
@Database(entities = [RiwayatPencarianDataModel::class], version = 1)
abstract class LocalModule : RoomDatabase() {
    abstract fun riwayatPencarianDao(): RiwayatPencarianDao

    companion object {
        @Volatile
        private var Object: LocalModule? = null

        @Provides
        @JvmStatic
        fun getDatabase(context: Context): LocalModule {
            if (Object == null) {
                synchronized(LocalModule::class.java) {
                    Object = Room.databaseBuilder(
                        context.applicationContext, LocalModule::class.java, "app_db1"
                    ).build()
                }
            }
            return Object as LocalModule
        }
    }
}