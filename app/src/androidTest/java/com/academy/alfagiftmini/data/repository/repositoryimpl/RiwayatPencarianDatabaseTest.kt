package com.academy.alfagiftmini.data.repository.repositoryimpl

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.test.filters.SmallTest
import com.academy.alfagiftmini.data.di.LocalModule
import com.google.common.truth.Truth
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.coroutines.coroutineContext

@RunWith(MockitoJUnitRunner::class)
@SmallTest
class RiwayatPencarianDatabaseTest {
    private lateinit var database: LocalModule

    @Before
    fun setup() {
        val context = Application().applicationContext
        database = Room.inMemoryDatabaseBuilder(
            context,
            LocalModule::class.java
        ).build()
    }

    @Test
    fun inputData() {
        val expected = RiwayatPencarianDummyDataProvider.provideDefaultData()
        val input = RiwayatPencarianDummyDataProvider.provideDefaultData()
        database.riwayatPencarianDao().insertPencarian(input)
        Truth.assertThat(database.riwayatPencarianDao().getData()).isEqualTo(listOf(expected))
    }

    @After
    fun closeDb() {
        database.close()
    }
}