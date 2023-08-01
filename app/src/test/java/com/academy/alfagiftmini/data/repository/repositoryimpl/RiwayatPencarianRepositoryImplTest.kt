package com.academy.alfagiftmini.data.repository.repositoryimpl

import com.academy.alfagiftmini.data.di.LocalModule
import com.academy.alfagiftmini.data.repository.local.riwayatpencarian.RiwayatPencarianDao
import com.academy.alfagiftmini.domain.riwayatpencarian.model.RiwayatPencarianDomainModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RiwayatPencarianRepositoryImplTest{
    @Mock
    lateinit var dbService: LocalModule
    @Mock
    lateinit var dbDao: RiwayatPencarianDao

    private lateinit var repo: RiwayatPencarianRepositoryImpl

    @Before
    fun setup() {
        repo = RiwayatPencarianRepositoryImpl(dbService)
    }

    @Test
    fun `valid data return default`() = runTest {
        val input = RiwayatPencarianDummy.initDataModelExample()
        val expected = RiwayatPencarianDummy.initExpectedResponse()
        Mockito.`when`(dbService.riwayatPencarianDao()).thenReturn(dbDao)
        Mockito.`when`(dbDao.getData()).thenReturn(input)
        repo.getRiwayatPencarian().collectLatest {
            assertEquals(it, expected)
        }
    }

    @Test
    fun `null data return default`() = runTest {
        val input = null
        val expected = emptyList<RiwayatPencarianDomainModel>()
        Mockito.`when`(dbService.riwayatPencarianDao()).thenReturn(dbDao)
        Mockito.`when`(dbDao.getData()).thenReturn(input)
        repo.getRiwayatPencarian().collectLatest {
            assertEquals(it, expected)
        }
    }
}