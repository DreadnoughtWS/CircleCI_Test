package com.academy.alfagiftmini.data.repository.network.produklist.hargaspesial

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.academy.alfagiftmini.data.DataUtils
import com.academy.alfagiftmini.data.repository.network.produklist.ProductListApiService
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListDetailDataModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel

class ProductListHargaSpesialNamaProdukPagingSource(
    private val apiService: ProductListApiService,
    private val type: Int,
    private val orderBy: String,
    private val sort: String
) : PagingSource<Int, ProductListDomainItemModel>() {
    override fun getRefreshKey(state: PagingState<Int, ProductListDomainItemModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductListDomainItemModel> {
        val position = params.key ?: 1
        return try {
            val responseStock = apiService.getProductStock()

            val responseProduct = apiService.getProductsOrder(
                page = position, limit = 10, order = orderBy, sort = sort
            )

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



            val dataSudahDiTransform = ProductListDetailDataModel.transforms(
                dataKodePromo, responseStock[0].productDetails ?: arrayListOf()
            )

            toLoadResult(
                dataSudahDiTransform,
                nextKey = if (responseProduct.isEmpty()) null else position + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    private fun toLoadResult(
        data: List<ProductListDomainItemModel>, prevKey: Int? = null, nextKey: Int? = null
    ): LoadResult<Int, ProductListDomainItemModel> {
        return LoadResult.Page(
            data = data, prevKey = prevKey, nextKey = nextKey
        )
    }

}