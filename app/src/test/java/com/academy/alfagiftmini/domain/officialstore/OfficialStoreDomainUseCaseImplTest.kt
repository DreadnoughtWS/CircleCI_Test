package com.academy.alfagiftmini.domain.officialstore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.OfficialStoreViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.collectDataForTest
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.getOrAwaitValueTest
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.modeltest.OfficialStoreModelTest
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class OfficialStoreDomainUseCaseImplTest {


  @get:Rule
  var rule: TestRule = InstantTaskExecutorRule()

  private val testDispatcher = TestCoroutineDispatcher()
  private val testScope = TestCoroutineScope(testDispatcher)

  private lateinit var officialStoreUseCase: OfficialStoreDomainUseCase
  private var officialStoreRepository = Mockito.mock(OfficialStoreDomainRepository::class.java)
  private lateinit var viewModel: OfficialStoreViewModel

  @Before
  fun setup() {
    officialStoreUseCase = OfficialStoreDomainUseCaseImpl(officialStoreRepository)
    viewModel = OfficialStoreViewModel(officialStoreUseCase)
    Dispatchers.setMain(testDispatcher)
  }

  @After
  fun setDown() {
    Dispatchers.resetMain()
    testScope.cleanupTestCoroutines()
  }

  @Test
  fun `Test Get 14 Official Store Success`() {
    runTest {
      Mockito.`when`(officialStoreRepository.get14OfficialStore())
        .thenReturn(flowOf(OfficialStoreModelTest.dummyOfficialStoreList()))

      officialStoreUseCase.get14OfficialStore().collect {
        Truth.assertThat(it).isNotEmpty()
        Truth.assertThat(it).isEqualTo(OfficialStoreModelTest.dummyOfficialStoreList())
      }

      Mockito.verify(officialStoreRepository).get14OfficialStore()
    }
  }

  @Test
  fun `Test Get 14 Official Store Null`() {
    runTest {
      Mockito.`when`(officialStoreRepository.get14OfficialStore())
        .thenReturn(flowOf(OfficialStoreModelTest.dummyOfficialStoreList()))

      officialStoreUseCase.get14OfficialStore().collect {
        Truth.assertThat(it[0].productImage).isNull()
      }
    }
  }


  @Test
  fun `Test Get All Official Store Success`() {
    runTest {
      Mockito.`when`(
        officialStoreRepository.getAllOfficialStore(
          viewModel.viewModelScope,
          "",
          PresentationUtils.TYPE_GET_ALL_OFFICIAL
        )
      )
        .thenReturn(flowOf(OfficialStoreModelTest.dummyOfficialStorePagingData()))

      officialStoreUseCase.getAllOfficialStore(
        viewModel.viewModelScope,
        "",
        PresentationUtils.TYPE_GET_ALL_OFFICIAL
      ).collectLatest {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items).isEqualTo(OfficialStoreModelTest.dummyOfficialStoreList())
      }

      Mockito.verify(officialStoreRepository).getAllOfficialStore(
        viewModel.viewModelScope,
        "",
        PresentationUtils.TYPE_GET_ALL_OFFICIAL
      )
    }
  }
  @Test
  fun `Test Get All Official Store Null`() {
    runTest {
      Mockito.`when`(
        officialStoreRepository.getAllOfficialStore(
          viewModel.viewModelScope,
          "",
          PresentationUtils.TYPE_GET_ALL_OFFICIAL
        )
      )
        .thenReturn(flowOf(OfficialStoreModelTest.dummyOfficialStorePagingData()))

      officialStoreUseCase.getAllOfficialStore(
        viewModel.viewModelScope,
        "",
        PresentationUtils.TYPE_GET_ALL_OFFICIAL
      ).collectLatest {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items[0].productImage).isNull()
      }
    }
  }

  @Test
  fun `Test Get Brands Success`() {
    runTest {
      Mockito.`when`(officialStoreRepository.getBrands("1")).thenReturn(
        flowOf(OfficialStoreModelTest.listDummyBrandOfficialStore())
      )

      officialStoreUseCase.getBrands("1").collectLatest {
        Truth.assertThat(it).isEqualTo(OfficialStoreModelTest.listDummyBrandOfficialStore())
      }

      Mockito.verify(officialStoreRepository).getBrands("1")

    }
  }

  @Test
  fun `Test Get Brands Null`() {
    runTest {
      Mockito.`when`(officialStoreRepository.getBrands("1")).thenReturn(
        flowOf(OfficialStoreModelTest.listDummyBrandOfficialStore())
      )

      officialStoreUseCase.getBrands("1").collectLatest {
        Truth.assertThat(it[1].brandName).isNull()
      }

    }
  }
}