package com.academy.alfagiftmini.data.repository.repositoryimpl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.academy.alfagiftmini.data.repository.network.officialstore.OfficialStoreApiService
import com.academy.alfagiftmini.data.repository.repositoryimpl.model.OfficialStoreDataModelTest
import com.academy.alfagiftmini.data.repository.repositoryimpl.model.OfficialStoreTransformTest
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainRepository
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCaseImpl
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.OfficialStoreViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.modeltest.OfficialStoreModelTest
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class OfficialStoreRepositoryImplTest {
  @get:Rule
  var rule: TestRule = InstantTaskExecutorRule()

  private val testDispatcher = TestCoroutineDispatcher()
  private val testScope = TestCoroutineScope(testDispatcher)
  private var officialStoreApiService = Mockito.mock(OfficialStoreApiService::class.java)
  private lateinit var officialStoreDomainRepository: OfficialStoreDomainRepository

  @Before
  fun setup() {
    officialStoreDomainRepository = OfficialStoreRepositoryImpl(officialStoreApiService)
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
      Mockito.`when`(officialStoreApiService.getAllOfficialStore(1, 15))
        .thenReturn(OfficialStoreDataModelTest.listDummyOfficialStoreDataModel())

      officialStoreDomainRepository.get14OfficialStore().collectLatest {
        Truth.assertThat(it).isNotEmpty()
      }

      Mockito.verify(officialStoreApiService).getAllOfficialStore(1, 15)
    }
  }

  @Test
  fun `Test Get Brands official Store Success`() {
    runTest {
      Mockito.`when`(officialStoreApiService.getBrands(""))
        .thenReturn(OfficialStoreDataModelTest.listDummyBrand())

      officialStoreDomainRepository.getBrands("").collectLatest {
        Truth.assertThat(it).isNotEmpty()
      }

      Mockito.verify(officialStoreApiService).getBrands("")
    }
  }


}