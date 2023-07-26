package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import com.academy.alfagiftmini.presentation.PresentationUtils.TYPE_GET_ALL_OFFICIAL
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.modeltest.OfficialStoreModelTest
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
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
class OfficialStoreViewModelTest {


  @Mock
  lateinit var officialStoreUseCase: OfficialStoreDomainUseCase

  @get:Rule
  var rule: TestRule = InstantTaskExecutorRule()

//  @get:Rule
//  var coroutineRule = MainDispatcherRule()

  private val testDispatcher = TestCoroutineDispatcher()
  private val testScope = TestCoroutineScope(testDispatcher)

  private lateinit var viewModel: OfficialStoreViewModel


  @Before
  fun setup() {
    viewModel = OfficialStoreViewModel(officialStoreUseCase)
    Dispatchers.setMain(testDispatcher)
  }

  @After
  fun setdown(){
    Dispatchers.resetMain()
    testScope.cleanupTestCoroutines()
  }





  @Test
  fun `Test Get 14 Official Store Null`(){
    runTest {
      Mockito.`when`(officialStoreUseCase.get14OfficialStore()).thenReturn(
        flowOf(OfficialStoreModelTest.dummyOfficialStoreList())
      )
      viewModel.get14OfficialStre()

      viewModel.officialStore14.getOrAwaitValueTest {
        Truth.assertThat(it[0].productImage).isNull()
      }
    }
  }

  @Test
  fun `Test Get 14 Official Store Success`() {
    testScope.runBlockingTest {
      Mockito.`when`(officialStoreUseCase.get14OfficialStore()).thenReturn(
        flowOf(OfficialStoreModelTest.dummyOfficialStoreList())
      )
      viewModel.get14OfficialStre()

        viewModel.officialStore14.getOrAwaitValueTest {
          Truth.assertThat(it).isEqualTo(OfficialStoreModelTest.dummyOfficialStoreList())
        }
        Mockito.verify(officialStoreUseCase).get14OfficialStore()



    }
  }

  @Test
  fun `Test Get All Official Store Null`(){
    testScope.runBlockingTest {
//      MockitoWhen()
      val tmp = viewModel.officialStore.value
      if(tmp != null){
        advanceUntilIdle()
        val result =tmp.collectDataForTest(testDispatcher)
        Truth.assertThat(result[0].productImage).isNull()
      }


    }
  }


  @Test
  fun `Test Get All Official Store Success`() {
    testScope.runBlockingTest {

      Mockito.`when`(
        officialStoreUseCase.getAllOfficialStore(
          viewModel.viewModelScope, "",
          TYPE_GET_ALL_OFFICIAL
        )
      ).thenReturn(
        flowOf(OfficialStoreModelTest.dummyOfficialStorePagingData())
      )

      viewModel.getAllOfficialStore("", TYPE_GET_ALL_OFFICIAL)

      val tmp = viewModel.officialStore.value
      if(tmp != null){
        advanceUntilIdle()
        val result =tmp.collectDataForTest(testDispatcher)
          Truth.assertThat(result).isEqualTo(OfficialStoreModelTest.dummyOfficialStoreList())
      }
    }
  }


  @Test
  fun `Test Get Brand Null`() {
    runTest {
      Mockito.`when`(officialStoreUseCase.getBrands("1")).thenReturn(
        flowOf(OfficialStoreModelTest.listDummyBrandOfficialStore())
      )
      viewModel.getBrands("1")


      viewModel.brand.getOrAwaitValueTest {
        println(it)
        Truth.assertThat(it[1].brandName).isNull()
      }


    }
  }

  @Test
  fun `Test Get Brands success`() {
    runTest {
      Mockito.`when`(officialStoreUseCase.getBrands("1")).thenReturn(
        flowOf(OfficialStoreModelTest.listDummyBrandOfficialStore())
      )
      viewModel.getBrands("1")


      viewModel.brand.getOrAwaitValueTest {
        println(it)
        Truth.assertThat(it).isEqualTo(OfficialStoreModelTest.listDummyBrandOfficialStore())
      }

      Mockito.verify(officialStoreUseCase).getBrands("1")

    }
  }

  @Test
  fun `Test Get Item Count With Int Success`() {
    val itemCount =  1
    viewModel.setItemCount(itemCount)
    val result = viewModel.itemCount.getOrAwaitValueTest {
      Truth.assertThat(it).isEqualTo(itemCount)
    }
  }

}