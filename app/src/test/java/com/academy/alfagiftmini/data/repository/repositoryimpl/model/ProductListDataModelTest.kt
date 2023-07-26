package com.academy.alfagiftmini.data.repository.repositoryimpl.model

import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListDetailDataModel
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListImagesDataModel
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListTebusMurahDataModel

object ProductListDataModelTest {
  fun dummyProductListTebusMurah() = ProductListTebusMurahDataModel(
     id= 1,
   accordionName= "test",
   promotionProductId= listOf(1)
  )

  fun listDummyProductListTebusMurah():List<ProductListTebusMurahDataModel>{
    return listOf(dummyProductListTebusMurah())
  }

  fun dummyProductListDetail() = ProductListDetailDataModel(
     productId= 1,
   productSku= "",
   productName= "",
   productDesc= "String",
   price= 0,
   productSpecialPrice= 0,
   productImages= listOf(dummyProductListImage()),
   productPickupAvailability= 0,
   productCategory= "",
   id= 1,
   kodeStore= "String",
   kodePromo= listOf(1,2),
   salesQuantity= 1,
   officialStoreId= 1,
   imgPreview103= "String"
  )

  fun dummyProductListImage():ProductListImagesDataModel{
    return ProductListImagesDataModel(
       type= "",
     url= listOf("a","b")
    )
  }

  fun listDummyProductListDetail():List<ProductListDetailDataModel>{
    return listOf(dummyProductListDetail())
  }
}