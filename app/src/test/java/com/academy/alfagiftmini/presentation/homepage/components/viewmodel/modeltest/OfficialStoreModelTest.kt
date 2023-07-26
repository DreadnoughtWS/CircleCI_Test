package com.academy.alfagiftmini.presentation.homepage.components.viewmodel.modeltest

import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStorebrandsDomainItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object OfficialStoreModelTest {
  fun dummyOfficialStore() = OfficialStoreDomainItemModel(
    id = 1,
    name = "madae",
    image = "https://c.alfagift.id/product/1/1_A12630003501_20210708151453027_small.jpg",
    productImage = null,
    brands = arrayListOf(dummyBrandsOfficialStore()),
    totalFollowers = 0
  )

  fun dummyBrandsOfficialStore() = OfficialStorebrandsDomainItemModel(
    brandId = 1,
    brandName = "nestle",
    brandImage = "https://c.alfagift.id/product/1/1_A08140041511_20210506104502580_small.jpg"
  )

  fun dummyBrandsOfficialStoreBrandNameBlank() = OfficialStorebrandsDomainItemModel(
    brandId = 1,
    brandName = "",
    brandImage = "https://c.alfagift.id/product/1/1_A08140041511_20210506104502580_small.jpg"
  )

  fun listFlowDummyBrandOfficialStore(): Flow<List<OfficialStorebrandsDomainItemModel>> {
    return flowOf(
      listOf(
        OfficialStorebrandsDomainItemModel(
          brandId = 1,
          brandName = "test",
          brandImage = "https://c.alfagift.id/product/1/1_A08140041511_20210506104502580_small.jpg"
        ),
        OfficialStorebrandsDomainItemModel(
          brandId = 2,
          brandName = "tast",
          brandImage = "https://c.alfagift.id/product/1/1_A08140041511_20210506104502580_small.jpg"
        ),
      )
    )

  }

  fun listDummyBrandOfficialStore(): List<OfficialStorebrandsDomainItemModel> {
    return listOf(
      OfficialStorebrandsDomainItemModel(
        brandId = 1,
        brandName = "test",
        brandImage = "https://c.alfagift.id/product/1/1_A08140041511_20210506104502580_small.jpg"
      ),
      OfficialStorebrandsDomainItemModel(
        brandId = 2,
        brandName = null,
        brandImage = "https://c.alfagift.id/product/1/1_A08140041511_20210506104502580_small.jpg"
      ),
    )

  }

  fun dummyOfficialStorePagingData():PagingData<OfficialStoreDomainItemModel>{
    return PagingData.from(listOf(dummyOfficialStore()))
  }

  fun dummyOfficialStoreList():List<OfficialStoreDomainItemModel>{
    return listOf(
      dummyOfficialStore()
    )
  }

}