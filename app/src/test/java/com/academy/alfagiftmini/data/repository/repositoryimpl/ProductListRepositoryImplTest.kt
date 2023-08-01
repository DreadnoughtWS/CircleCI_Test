package com.academy.alfagiftmini.data.repository.repositoryimpl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.academy.alfagiftmini.data.repository.network.officialstore.OfficialStoreApiService
import com.academy.alfagiftmini.data.repository.network.produckcategories.ProductCategoriesApiService
import com.academy.alfagiftmini.data.repository.network.produklist.ProductListApiService
import com.academy.alfagiftmini.data.repository.repositoryimpl.model.ProductListDataModelTest
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainRepository
import com.academy.alfagiftmini.domain.produklist.ProductListDomainRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ProductListRepositoryImplTest {
  @get:Rule
  var rule: TestRule = InstantTaskExecutorRule()

  private val testDispatcher = TestCoroutineDispatcher()
  private val testScope = TestCoroutineScope(testDispatcher)
  private var productListApiService = Mockito.mock(ProductListApiService::class.java)
  private var categoryApiService = Mockito.mock(ProductCategoriesApiService::class.java)
  private lateinit var productListDomainRepository: ProductListDomainRepository

  @Before
  fun setup() {
    productListDomainRepository =
      ProductListRepositoryImpl(productListApiService, categoryApiService)
    Dispatchers.setMain(testDispatcher)
  }

  @After
  fun setDown() {
    Dispatchers.resetMain()
    testScope.cleanupTestCoroutines()
  }

  @Test
  fun `Test Get Product Tebus Murah Success`() {
    runTest {
      Mockito.`when`(productListApiService.getTebusMurah())
        .thenReturn(ProductListDataModelTest.listDummyProductListTebusMurah())
      Mockito.`when`(productListApiService.getMultipleProducts("1")).thenReturn(
        ProductListDataModelTest.listDummyProductListDetail()
      )
      productListDomainRepository.getProductTebusMurah().collect {
        Truth.assertThat(it).isNotEmpty()
      }

      Mockito.verify(productListApiService).getTebusMurah()
      Mockito.verify(productListApiService).getMultipleProducts("1")
    }
  }

  @Test
  fun `Test Get Product Get By Name Success`() {
    runTest {
      Mockito.`when`(productListApiService.getProductByName(1, ""))
        .thenReturn(ProductListDataModelTest.listDummyProductListDetail())

      productListDomainRepository.getProductByName("").collectLatest {
        Truth.assertThat(it).isNotEmpty()
      }

      Mockito.verify(productListApiService).getProductByName(1,"")

    }
  }
}