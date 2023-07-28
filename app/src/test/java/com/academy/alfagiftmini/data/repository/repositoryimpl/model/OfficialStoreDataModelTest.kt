package com.academy.alfagiftmini.data.repository.repositoryimpl.model

import com.academy.alfagiftmini.data.repository.network.officialstore.model.OfficialStoreBrandsDataModel
import com.academy.alfagiftmini.data.repository.network.officialstore.model.OfficialStoreDetailDataModel
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStorebrandsDomainItemModel
import com.academy.alfagiftmini.presentation.homepage.components.viewmodel.modeltest.OfficialStoreModelTest
import com.google.gson.annotations.SerializedName

object OfficialStoreDataModelTest {
  fun dummyOfficialStoreDataModel() = OfficialStoreDetailDataModel(
     id= 1,
   name= "",
   image= "",
    productImage= null,
   brands= listOf(dummyOfficialStoreBrandsDataModel()),
   totalFollowers= 1
  )

  fun dummyOfficialStoreBrandsDataModel() = OfficialStoreBrandsDataModel(
     brandId= 1,
   brandName= "",
   brandImage= ""
  )

  fun listDummyOfficialStoreDataModel():List<OfficialStoreDetailDataModel>{
    return listOf(dummyOfficialStoreDataModel())
  }

  fun dummyBrand() = OfficialStoreBrandsDataModel(
     brandId= 0,
   brandName= "",
   brandImage= ""
  )

  fun listDummyBrand():List<OfficialStoreBrandsDataModel>{
    return listOf(dummyBrand())
  }



}