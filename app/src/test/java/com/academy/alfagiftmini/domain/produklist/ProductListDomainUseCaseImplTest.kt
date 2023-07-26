package com.academy.alfagiftmini.domain.produklist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainRepository
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCaseImpl
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.OfficialStoreViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.ProductListViewModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.collectDataForTest
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.modeltest.ProductListModelTest
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ProductListDomainUseCaseImplTest {
  @get:Rule
  var rule: TestRule = InstantTaskExecutorRule()

  private val testDispatcher = TestCoroutineDispatcher()
  private val testScope = TestCoroutineScope(testDispatcher)

  private lateinit var productListUseCase: ProductListDomainUseCase
  private var productListRepository = Mockito.mock(ProductListDomainRepository::class.java)
  private lateinit var viewModel: ProductListViewModel

  @Before
  fun setup() {
    productListUseCase = ProductListDomainUseCaseImpl(productListRepository)
    viewModel = ProductListViewModel(productListUseCase)
    Dispatchers.setMain(testDispatcher)
  }

  @After
  fun setDown() {
    Dispatchers.resetMain()
    testScope.cleanupTestCoroutines()
  }

  @Test
  fun `Test Get Product List Gratis Product Success`() {
    runTest {
      Mockito.`when`(
        productListRepository.getProductGratisProduct(
          viewModel.viewModelScope,
          PresentationUtils.TYPE_GRATIS_PRODUK
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      productListUseCase.getProductGratisProduct(
        viewModel.viewModelScope,
        PresentationUtils.TYPE_GRATIS_PRODUK
      ).collect {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items).isEqualTo(ProductListModelTest.ListDummyProductList())
      }

      Mockito.verify(productListRepository).getProductGratisProduct(
        viewModel.viewModelScope,
        PresentationUtils.TYPE_GRATIS_PRODUK
      )
    }
  }

  @Test
  fun `Test Get Product List Gratis Product Null`() {
    runTest {
      Mockito.`when`(
        productListRepository.getProductGratisProduct(
          viewModel.viewModelScope,
          PresentationUtils.TYPE_GRATIS_PRODUK
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))
      productListUseCase.getProductGratisProduct(
        viewModel.viewModelScope,
        PresentationUtils.TYPE_GRATIS_PRODUK
      ).collect {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items[0].productSpecialPrice).isNull()
      }
    }
  }

  @Test
  fun `Test Get Product Gratis Product Order Success`() {
    runTest {
      Mockito.`when`(
        productListRepository.getProductGratisProductOrder(
          viewModel.viewModelScope,
          PresentationUtils.TYPE_GRATIS_PRODUK,
          "",
          ""
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      productListUseCase.getProductGratisProductOrder(
        viewModel.viewModelScope,
        PresentationUtils.TYPE_GRATIS_PRODUK,
        "",
        ""
      ).collect {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items).isEqualTo(ProductListModelTest.ListDummyProductList())
      }

      Mockito.verify(productListRepository).getProductGratisProductOrder(
        viewModel.viewModelScope,
        PresentationUtils.TYPE_GRATIS_PRODUK, "", ""
      )
    }
  }

  @Test
  fun `Test Get Product Gratis Product Order Null`() {
    runTest {
      Mockito.`when`(
        productListRepository.getProductGratisProductOrder(
          viewModel.viewModelScope,
          PresentationUtils.TYPE_GRATIS_PRODUK,
          "",
          ""
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      productListUseCase.getProductGratisProductOrder(
        viewModel.viewModelScope,
        PresentationUtils.TYPE_GRATIS_PRODUK,
        "",
        ""
      ).collect {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items[0].productSpecialPrice).isNull()
      }

    }
  }

  @Test
  fun `Test Get Product List In Detail Official Store Success`() {
    runTest {
      Mockito.`when`(
        productListRepository.getDetailOfficialStoreProductPromosi(
          viewModel.viewModelScope,
          1
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      productListUseCase.getDetailOfficialStoreProductPromosi(viewModel.viewModelScope,1).collect {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items).isEqualTo(ProductListModelTest.ListDummyProductList())
      }

      Mockito.verify(productListRepository).getDetailOfficialStoreProductPromosi(
        viewModel.viewModelScope,
        1
      )
    }
  }

  @Test
  fun `Test Get Product List In Detail Official Store Null`() {
    runTest {
      Mockito.`when`(
        productListRepository.getDetailOfficialStoreProductPromosi(
          viewModel.viewModelScope,
          1
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      productListUseCase.getDetailOfficialStoreProductPromosi(viewModel.viewModelScope,1).collect {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items[0].productSpecialPrice).isNull()
      }

    }
  }

  @Test
  fun `Test Get Product List Order In Detail Official Store Success`() {
    runTest {
      Mockito.`when`(
        productListRepository.getDetailOfficialStoreOrder(
          viewModel.viewModelScope,
          "",
          "",
          1
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      productListUseCase.getDetailOfficialStoreOrder(viewModel.viewModelScope,"", "", 1).collect {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items).isEqualTo(ProductListModelTest.ListDummyProductList())
      }

      Mockito.verify(productListRepository).getDetailOfficialStoreOrder(
        viewModel.viewModelScope,
        "",
        "",
        1
      )

    }
  }

  @Test
  fun `Test Get Product List Order In Detail Official Store Null`() {
    runTest {
      Mockito.`when`(
        productListRepository.getDetailOfficialStoreOrder(
          viewModel.viewModelScope,
          "",
          "",
          1
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      productListUseCase.getDetailOfficialStoreOrder(viewModel.viewModelScope,"", "", 1).collect {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items[0].productSpecialPrice).isNull()
      }
    }
  }

  @Test
  fun `Test Get Product List Tebus Murah Success`() {
    runTest {
      Mockito.`when`(
        productListRepository.getProductTebusMurah(
        )
      ).thenReturn(flowOf(ProductListModelTest.ListDummyProductListTebusMurah()))

      productListUseCase.getProductTebusMurah().collect {
        Truth.assertThat(it).isNotNull()
        Truth.assertThat(it).isEqualTo(ProductListModelTest.ListDummyProductListTebusMurah())
      }

      Mockito.verify(productListRepository).getProductTebusMurah()

    }
  }


  @Test
  fun `Test Get Product List Tebus Murah Null`() {
    runTest {
      Mockito.`when`(
        productListRepository.getProductTebusMurah(
        )
      ).thenReturn(flowOf(ProductListModelTest.ListDummyProductListTebusMurah()))

      productListUseCase.getProductTebusMurah().collect {
        Truth.assertThat(it[0].tebusMurahProduct[0].productSpecialPrice).isNull()
      }
    }
  }

  @Test
  fun `Test Get Preview Product Name Success`() {
    runTest {
      Mockito.`when`(
        productListRepository.getProductByName(
          ""
        )
      ).thenReturn(flowOf(ProductListModelTest.ListDummyProductListProductByName()))

      productListUseCase.getProductByName("").collect {
        Truth.assertThat(it).isEqualTo(ProductListModelTest.ListDummyProductListProductByName())
      }

      Mockito.verify(productListRepository).getProductByName("")
    }
  }

  @Test
  fun `Test Get Preview Product Name Null`() {
    runTest {
      Mockito.`when`(
        productListRepository.getProductByName(
          ""
        )
      ).thenReturn(flowOf(ProductListModelTest.ListDummyProductListProductByName()))

      productListUseCase.getProductByName("").collect {
        Truth.assertThat(it[0].productSpecialPrice).isNull()
      }

    }
  }

  @Test
  fun `Test Get Product Search Product Order Success`() {
    runTest {
      Mockito.`when`(
        productListRepository.getProductSearchProductOrder(
          viewModel.viewModelScope,
          "",
          "",
          "",
          PresentationUtils.TYPE_SEARCH_OFFICIAL
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      productListUseCase.getProductSearchProductOrder(viewModel.viewModelScope,"", "", "", PresentationUtils.TYPE_SEARCH_OFFICIAL)
        .collect {
          val items = it.collectDataForTest(testDispatcher)
          Truth.assertThat(items).isEqualTo(ProductListModelTest.ListDummyProductList())
        }

      Mockito.verify(productListRepository).getProductSearchProductOrder(
        viewModel.viewModelScope,
        "",
        "",
        "",
        PresentationUtils.TYPE_SEARCH_OFFICIAL
      )

    }
  }

  @Test
  fun `Test Get Product Search Product Order Null`() {
    runTest {
      Mockito.`when`(
        productListRepository.getProductSearchProductOrder(
          viewModel.viewModelScope,
          "",
          "",
          "",
          PresentationUtils.TYPE_SEARCH_OFFICIAL
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      productListUseCase.getProductSearchProductOrder(viewModel.viewModelScope,"", "", "", PresentationUtils.TYPE_SEARCH_OFFICIAL)
        .collect {
          val items = it.collectDataForTest(testDispatcher)
          Truth.assertThat(items[0].productSpecialPrice).isNull()
        }

    }
  }

  @Test
  fun `Test Get Product Search Product Success`() {
    runTest {
      Mockito.`when`(
        productListRepository.getProductSearchProduct(
          viewModel.viewModelScope,
          "",
          PresentationUtils.TYPE_SEARCH_OFFICIAL
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      productListUseCase.getProductSearchProduct(viewModel.viewModelScope,"", PresentationUtils.TYPE_SEARCH_OFFICIAL)
        .collect {
          val items = it.collectDataForTest(testDispatcher)
          Truth.assertThat(items).isEqualTo(ProductListModelTest.ListDummyProductList())
        }

      Mockito.verify(productListRepository).getProductSearchProduct(
        viewModel.viewModelScope,
        "",
        PresentationUtils.TYPE_SEARCH_OFFICIAL
      )

    }
  }

  @Test
  fun `Test Get Product Search Product Null`() {
    runTest {
      Mockito.`when`(
        productListRepository.getProductSearchProduct(
          viewModel.viewModelScope,
          "",
          PresentationUtils.TYPE_SEARCH_OFFICIAL
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      productListUseCase.getProductSearchProduct(viewModel.viewModelScope,"", PresentationUtils.TYPE_SEARCH_OFFICIAL)
        .collect {
          val items = it.collectDataForTest(testDispatcher)
          Truth.assertThat(items[0].productSpecialPrice).isNull()
        }

    }
  }

  @Test
  fun `Test Get Banner Product Success`() {
    runTest {
      Mockito.`when`(
        productListRepository.getBannerProduct(
          viewModel.viewModelScope,
          1,
          "","",PresentationUtils.TYPE_PROMOSI
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      productListUseCase.getBannerProduct(viewModel.viewModelScope,1,"","",PresentationUtils.TYPE_PROMOSI)
        .collect {
          val items = it.collectDataForTest(testDispatcher)
          Truth.assertThat(items).isEqualTo(ProductListModelTest.ListDummyProductList())
        }

      Mockito.verify(productListRepository).getBannerProduct(
        viewModel.viewModelScope,
        1,
        "","",PresentationUtils.TYPE_PROMOSI
      )
    }
  }

  @Test
  fun `Test Get Banner Product Null`(){
    runTest {
      Mockito.`when`(
        productListRepository.getBannerProduct(
          viewModel.viewModelScope,
          1,
          "","",PresentationUtils.TYPE_PROMOSI
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      productListUseCase.getBannerProduct(viewModel.viewModelScope,1,"","",PresentationUtils.TYPE_PROMOSI)
        .collect {
          val items = it.collectDataForTest(testDispatcher)
          Truth.assertThat(items[0].productSpecialPrice).isNull()
        }
    }
  }

  @Test
  fun `Test Get Product By Category Success`(){
    runTest {
      Mockito.`when`(productListRepository.getProductByCategory(
        viewModel.viewModelScope,
        "","","","",""
      )).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      productListUseCase.getProductByCategory(viewModel.viewModelScope,"","","","","").collect{
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items).isEqualTo(ProductListModelTest.ListDummyProductList())
      }

      Mockito.verify(productListRepository).getProductByCategory(
        viewModel.viewModelScope,
        "","","","",""
      )
    }
  }

  @Test
  fun `Test Get Product By Category Null`(){
    runTest {
      Mockito.`when`(productListRepository.getProductByCategory(
        viewModel.viewModelScope,
        "","","","",""
      )).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      productListUseCase.getProductByCategory(viewModel.viewModelScope,"","","","","").collect{
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items[0].productSpecialPrice).isNull()
      }
    }
  }



}