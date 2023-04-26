package com.academy.alfagiftmini.data.repository.netwok.produklist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.academy.alfagiftmini.data.DataUtils
import com.academy.alfagiftmini.data.repository.netwok.produklist.model.ProductListDetailDataModel
import com.academy.alfagiftmini.data.repository.netwok.produklist.model.ProductListPromotionProductDataModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel

class ProductListGratisProductPagingSource(
    private val apiService: ProductListApiService, private val type: Int
) : PagingSource<Int, ProductListPromotionProductDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, ProductListPromotionProductDomainModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductListPromotionProductDomainModel> {
        val position = params.key ?: 1
        return try {
            println("MASUK 5")
            val responseProduct = apiService.getAllProduct(page = position, limit = 5)
            println("MASUK 6")
            val responseSale = apiService.getPromotionProduct()
            println("MASUK 7")
            val responseStock = apiService.getProductStock()
            println("MASUK 8")

            val dataKodePromo: ArrayList<ProductListDetailDataModel> = arrayListOf()

            when (type) {
                DataUtils.TYPE_HARGA_SPESIAL -> {
                    responseProduct.forEach {
                        it.kodePromo?.forEach { kode ->
                            if (kode == DataUtils.TYPE_HARGA_SPESIAL) {
                                dataKodePromo.add(it)
                            }
                        }
                    }
                }
                DataUtils.TYPE_PAKET -> {
                    responseProduct.forEach {
                        it.kodePromo?.forEach { kode ->
                            if (kode == DataUtils.TYPE_PAKET) {
                                dataKodePromo.add(it)
                            }
                        }
                    }
                }
                DataUtils.TYPE_GRATIS_PRODUK -> {
                    responseProduct.forEach {
                        it.kodePromo?.forEach { kode ->
                            if (kode == DataUtils.TYPE_GRATIS_PRODUK) {
                                dataKodePromo.add(it)
                            }
                        }
                    }
                }
                DataUtils.TYPE_TEBUS_MURAH -> {
                    responseProduct.forEach {
                        it.kodePromo?.forEach { kode ->
                            if (kode == DataUtils.TYPE_TEBUS_MURAH) {
                                dataKodePromo.add(it)
                            }
                        }
                    }
                }
            }


            val dataSudahDiTransform = ProductListPromotionProductDataModel.transforms(
                dataKodePromo, responseSale, responseStock[0].productDetails ?: arrayListOf()
            )
            println(dataKodePromo.size)

            toLoadResult(
                dataSudahDiTransform,
                nextKey = if (responseProduct.isEmpty()) null else position + 1
            )
        } catch (e: java.lang.Exception) {
            println(e.message)
            LoadResult.Error(e)
        }
    }

    private fun toLoadResult(
        data: List<ProductListPromotionProductDomainModel>,
        prevKey: Int? = null,
        nextKey: Int? = null
    ): LoadResult<Int, ProductListPromotionProductDomainModel> {
        return LoadResult.Page(
            data = data, prevKey = prevKey, nextKey = nextKey
        )
    }


}