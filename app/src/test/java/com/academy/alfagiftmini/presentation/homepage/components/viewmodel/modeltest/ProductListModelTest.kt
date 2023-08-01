package com.academy.alfagiftmini.presentation.homepage.components.viewmodel.modeltest

import androidx.paging.PagingData
import androidx.paging.PagingData.Companion
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListDetailTebusMurahDomainModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListImagesDomainModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListTebusMurahDomainModel
import org.mockito.internal.matchers.Null

object ProductListModelTest {
  fun dummyProductList() = ProductListPromotionProductDomainModel(
    productId = 1000,
    productSku = "productSKU",
    productName = "productName",
    productDesc = "test",
    price = 100,
    productSpecialPrice = null,
    productImages = arrayListOf(dummyProductListImage()),
    productPickupAvailability = 1, // 1=toko, 0=gudang
    productCategory = "",
    id = 1,
    kodeStore = "",
    kodePromo = arrayListOf(1, 2),
    stock = null,
    salesQuantity = null,
    officialStoreId = 0,
    productGratis = arrayListOf(1, 2, 3),
    imgPreview103 = null,
  )

  fun dummyProductListImage() = ProductListImagesDomainModel(
    type = "image",
    url = listOf("1", "2")
  )

  fun dummyProductListPagingData(): PagingData<ProductListPromotionProductDomainModel> {
    return PagingData.from(listOf(dummyProductList()))
  }

  fun ListDummyProductList(): List<ProductListPromotionProductDomainModel> {
    return listOf(dummyProductList())
  }

  fun DummyProductListTebusMurah() = ProductListTebusMurahDomainModel(
    id = 0,
    accordionName = "",
    tebusMurahProduct = listOf(DummyProductListItemTebusMurah())
  )

  fun DummyProductListItemTebusMurah() = ProductListDetailTebusMurahDomainModel(
    productId = 10,
    productName = "String",
    price = 100,
    productSpecialPrice = null,
    productImages = listOf(dummyProductListImage()),
    productPickupAvailability = 1, // 1=toko, 0=gudang
    id = 1,
  )

  fun ListDummyProductListTebusMurah(): List<ProductListTebusMurahDomainModel> {
    return listOf(DummyProductListTebusMurah())
  }

  fun DummyProductListProductByName(): ProductListDomainItemModel {
    return ProductListDomainItemModel(
      productId = 1000,
      productSku = "productSKU",
      productName = "productName",
      productDesc = "test",
      price = 100,
      productSpecialPrice = null,
      productImages = arrayListOf(dummyProductListImage()),
      productPickupAvailability = 1, // 1=toko, 0=gudang
      productCategory = "",
      id = 1,
      kodeStore = "",
      kodePromo = arrayListOf(1, 2),
      stock = null,
      salesQuantity = null,
      officialStoreId = 0,
      imgPreview103 = null,
    )
  }

  fun ListDummyProductListProductByName():List<ProductListDomainItemModel>{
    return listOf(DummyProductListProductByName())
  }
}