package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import com.academy.alfagiftmini.domain.produklist.ProductListDomainUseCase
import com.academy.alfagiftmini.presentation.PresentationUtils
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.modeltest.ProductListModelTest
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
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
class ProductListViewModelTest {
  @Mock
  lateinit var productListDomainUseCase: ProductListDomainUseCase

  @get:Rule
  var rule: TestRule = InstantTaskExecutorRule()

  private val testDispatcher = TestCoroutineDispatcher()
  private val testScope = TestCoroutineScope(testDispatcher)

  private lateinit var viewModel: ProductListViewModel

  @Before
  fun setup() {
    viewModel = ProductListViewModel(productListDomainUseCase)
    Dispatchers.setMain(testDispatcher)
  }

  @After
  fun setdown() {
    Dispatchers.resetMain()
    testScope.cleanupTestCoroutines()
  }

  @Test
  fun `Test Get Product List Gratis Product Success`() {
    runTest {
      Mockito.`when`(
        productListDomainUseCase.getProductGratisProduct(
          viewModel.viewModelScope,
          PresentationUtils.TYPE_GRATIS_PRODUK
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      viewModel.getProductGratisProduct(PresentationUtils.TYPE_GRATIS_PRODUK).collect {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items).isEqualTo(ProductListModelTest.ListDummyProductList())
      }

      Mockito.verify(productListDomainUseCase).getProductGratisProduct(
        viewModel.viewModelScope,
        PresentationUtils.TYPE_GRATIS_PRODUK
      )
    }
  }

  @Test
  fun `Test Get Product List Gratis Product Null`() {
    runTest {
      Mockito.`when`(
        productListDomainUseCase.getProductGratisProduct(
          viewModel.viewModelScope,
          PresentationUtils.TYPE_GRATIS_PRODUK
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))
      viewModel.getProductGratisProduct(PresentationUtils.TYPE_GRATIS_PRODUK).collect {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items[0].productSpecialPrice).isNull()
      }
    }
  }

  @Test
  fun `Test Get Product Gratis Product Order Success`() {
    runTest {
      Mockito.`when`(
        productListDomainUseCase.getProductGratisProductOrder(
          viewModel.viewModelScope,
          PresentationUtils.TYPE_GRATIS_PRODUK,
          "",
          ""
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      viewModel.getProductGratisProductOrder(PresentationUtils.TYPE_GRATIS_PRODUK, "", "").collect {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items).isEqualTo(ProductListModelTest.ListDummyProductList())
      }

      Mockito.verify(productListDomainUseCase).getProductGratisProductOrder(
        viewModel.viewModelScope,
        PresentationUtils.TYPE_GRATIS_PRODUK, "", ""
      )
    }
  }

  @Test
  fun `Test Get Product Gratis Product Order Null`() {
    runTest {
      Mockito.`when`(
        productListDomainUseCase.getProductGratisProductOrder(
          viewModel.viewModelScope,
          PresentationUtils.TYPE_GRATIS_PRODUK,
          "",
          ""
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      viewModel.getProductGratisProductOrder(PresentationUtils.TYPE_GRATIS_PRODUK, "", "").collect {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items[0].productSpecialPrice).isNull()
      }

    }
  }

  @Test
  fun `Test Get Product List In Detail Official Store Success`() {
    runTest {
      Mockito.`when`(
        productListDomainUseCase.getDetailOfficialStoreProductPromosi(
          viewModel.viewModelScope,
          1
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      viewModel.getDetailOfficialStorePromosiProduct(1).collect {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items).isEqualTo(ProductListModelTest.ListDummyProductList())
      }

      Mockito.verify(productListDomainUseCase).getDetailOfficialStoreProductPromosi(
        viewModel.viewModelScope,
        1
      )
    }
  }

  @Test
  fun `Test Get Product List In Detail Official Store Null`() {
    runTest {
      Mockito.`when`(
        productListDomainUseCase.getDetailOfficialStoreProductPromosi(
          viewModel.viewModelScope,
          1
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      viewModel.getDetailOfficialStorePromosiProduct(1).collect {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items[0].productSpecialPrice).isNull()
      }

    }
  }

  @Test
  fun `Test Get Product List Order In Detail Official Store Success`() {
    runTest {
      Mockito.`when`(
        productListDomainUseCase.getDetailOfficialStoreOrder(
          viewModel.viewModelScope,
          "",
          "",
          1
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      viewModel.getDetailOffiialStoreOrder("", "", 1).collect {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items).isEqualTo(ProductListModelTest.ListDummyProductList())
      }

      Mockito.verify(productListDomainUseCase).getDetailOfficialStoreOrder(
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
        productListDomainUseCase.getDetailOfficialStoreOrder(
          viewModel.viewModelScope,
          "",
          "",
          1
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      viewModel.getDetailOffiialStoreOrder("", "", 1).collect {
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items[0].productSpecialPrice).isNull()
      }
    }
  }


  @Test
  fun `Test Get Product List Tebus Murah Success`() {
    runTest {
      Mockito.`when`(
        productListDomainUseCase.getProductTebusMurah(
        )
      ).thenReturn(flowOf(ProductListModelTest.ListDummyProductListTebusMurah()))

      viewModel.getProductListTebusMurah().collect {
        Truth.assertThat(it).isNotNull()
        Truth.assertThat(it).isEqualTo(ProductListModelTest.ListDummyProductListTebusMurah())
      }

      Mockito.verify(productListDomainUseCase).getProductTebusMurah()

    }
  }


  @Test
  fun `Test Get Product List Tebus Murah Null`() {
    runTest {
      Mockito.`when`(
        productListDomainUseCase.getProductTebusMurah(
        )
      ).thenReturn(flowOf(ProductListModelTest.ListDummyProductListTebusMurah()))

      viewModel.getProductListTebusMurah().collect {
        Truth.assertThat(it[0].tebusMurahProduct[0].productSpecialPrice).isNull()
      }
    }
  }

  @Test
  fun `Test Get Preview Product Name Success`() {
    runTest {
      Mockito.`when`(
        productListDomainUseCase.getProductByName(
          ""
        )
      ).thenReturn(flowOf(ProductListModelTest.ListDummyProductListProductByName()))

      viewModel.getPreviewProductName("").collect {
        Truth.assertThat(it).isEqualTo(ProductListModelTest.ListDummyProductListProductByName())
      }

      Mockito.verify(productListDomainUseCase).getProductByName("")
    }
  }

  @Test
  fun `Test Get Preview Product Name Null`() {
    runTest {
      Mockito.`when`(
        productListDomainUseCase.getProductByName(
          ""
        )
      ).thenReturn(flowOf(ProductListModelTest.ListDummyProductListProductByName()))

      viewModel.getPreviewProductName("").collect {
        Truth.assertThat(it[0].productSpecialPrice).isNull()
      }

    }
  }

  @Test
  fun `Test Get Product Search Product Order Success`() {
    runTest {
      Mockito.`when`(
        productListDomainUseCase.getProductSearchProductOrder(
          viewModel.viewModelScope,
          "",
          "",
          "",
          PresentationUtils.TYPE_SEARCH_OFFICIAL
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      viewModel.getProductSearchProductOrder("", "", "", PresentationUtils.TYPE_SEARCH_OFFICIAL)
        .collect {
          val items = it.collectDataForTest(testDispatcher)
          Truth.assertThat(items).isEqualTo(ProductListModelTest.ListDummyProductList())
        }

      Mockito.verify(productListDomainUseCase).getProductSearchProductOrder(
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
        productListDomainUseCase.getProductSearchProductOrder(
          viewModel.viewModelScope,
          "",
          "",
          "",
          PresentationUtils.TYPE_SEARCH_OFFICIAL
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      viewModel.getProductSearchProductOrder("", "", "", PresentationUtils.TYPE_SEARCH_OFFICIAL)
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
        productListDomainUseCase.getProductSearchProduct(
          viewModel.viewModelScope,
          "",
          PresentationUtils.TYPE_SEARCH_OFFICIAL
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      viewModel.getProductSearchProduct("", PresentationUtils.TYPE_SEARCH_OFFICIAL)
        .collect {
          val items = it.collectDataForTest(testDispatcher)
          Truth.assertThat(items).isEqualTo(ProductListModelTest.ListDummyProductList())
        }

      Mockito.verify(productListDomainUseCase).getProductSearchProduct(
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
        productListDomainUseCase.getProductSearchProduct(
          viewModel.viewModelScope,
          "",
          PresentationUtils.TYPE_SEARCH_OFFICIAL
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      viewModel.getProductSearchProduct("", PresentationUtils.TYPE_SEARCH_OFFICIAL)
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
        productListDomainUseCase.getBannerProduct(
          viewModel.viewModelScope,
          1,
          "","",PresentationUtils.TYPE_PROMOSI
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      viewModel.getBannerProduct(1,"","",PresentationUtils.TYPE_PROMOSI)
        .collect {
          val items = it.collectDataForTest(testDispatcher)
          Truth.assertThat(items).isEqualTo(ProductListModelTest.ListDummyProductList())
        }

      Mockito.verify(productListDomainUseCase).getBannerProduct(
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
        productListDomainUseCase.getBannerProduct(
          viewModel.viewModelScope,
          1,
          "","",PresentationUtils.TYPE_PROMOSI
        )
      ).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      viewModel.getBannerProduct(1,"","",PresentationUtils.TYPE_PROMOSI)
        .collect {
          val items = it.collectDataForTest(testDispatcher)
          Truth.assertThat(items[0].productSpecialPrice).isNull()
        }
    }
  }

  @Test
  fun `Test Get Product By Category Success`(){
    runTest {
      Mockito.`when`(productListDomainUseCase.getProductByCategory(
        viewModel.viewModelScope,
        "","","","",""
      )).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      viewModel.getProductByCategory(viewModel.viewModelScope,"","","","","").collect{
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items).isEqualTo(ProductListModelTest.ListDummyProductList())
      }

      Mockito.verify(productListDomainUseCase).getProductByCategory(
        viewModel.viewModelScope,
        "","","","",""
      )
    }
  }

  @Test
  fun `Test Get Product By Category Null`(){
    runTest {
      Mockito.`when`(productListDomainUseCase.getProductByCategory(
        viewModel.viewModelScope,
        "","","","",""
      )).thenReturn(flowOf(ProductListModelTest.dummyProductListPagingData()))

      viewModel.getProductByCategory(viewModel.viewModelScope,"","","","","").collect{
        val items = it.collectDataForTest(testDispatcher)
        Truth.assertThat(items[0].productSpecialPrice).isNull()
      }
    }
  }

  @Test
  fun `Test Get Item Amount Success`(){
    val itemCount = 1
    viewModel.setItemAmount(itemCount)
    viewModel.itemCount.getOrAwaitValueTest {
      Truth.assertThat(it).isEqualTo(itemCount)
    }
  }
}