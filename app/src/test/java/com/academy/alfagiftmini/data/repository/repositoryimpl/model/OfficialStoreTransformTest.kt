package com.academy.alfagiftmini.data.repository.repositoryimpl.model

import com.academy.alfagiftmini.data.repository.network.officialstore.model.OfficialStoreDetailDataModel
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStorebrandsDomainItemModel

class OfficialStoreTransformTest {
  companion object{
    fun transformsFrom_ListOfficialStoreDetailDataModel_To_ListOfficialStoreDomainItemModel(model: List<OfficialStoreDetailDataModel>): List<OfficialStoreDomainItemModel> {
      return model.map {
        transformFrom_ListOfficialStoreDetailDataModel_To_ListOfficialStoreDomainItemModel(
          OfficialStoreDetailDataModel(
            id = it.id,
            name = it.name,
            image = it.image,
            productImage = it.productImage,
            brands = it.brands,
            totalFollowers = it.totalFollowers
          )
        )
      }
    }
    fun transformFrom_ListOfficialStoreDetailDataModel_To_ListOfficialStoreDomainItemModel(model:OfficialStoreDetailDataModel):OfficialStoreDomainItemModel{
      return OfficialStoreDomainItemModel(
        id = model.id?:1,
        name= model.name?:"",
        image= model.image?:"",
        productImage= model.productImage ?: null,
        brands= model.brands?.map {
          OfficialStorebrandsDomainItemModel(
            brandId = it.brandId ?: 0, brandName = it.brandName ?: "", brandImage = it.brandImage?:""
          )
        } ?: listOf(),
        totalFollowers= model.totalFollowers ?:1
      )
    }
  }


}