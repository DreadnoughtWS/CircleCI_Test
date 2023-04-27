package com.academy.alfagiftmini.data.repository.network.produklist.detailofficialstore

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.academy.alfagiftmini.data.repository.network.produklist.ProductListApiService
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListDetailDataModel
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListPromotionProductDataModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel

class DetailOfficialStoreNamaDanTerlarisPagingSource(
    private val apiService: ProductListApiService,
    private val orderBy: String,
    private val sort: String,
    private val officialStoreId: Int
) : PagingSource<Int, ProductListPromotionProductDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, ProductListPromotionProductDomainModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductListPromotionProductDomainModel> {
        val position = params.key ?: 1

        return try{
            val responseStock = apiService.getProductStock()
            val responseSale = apiService.getPromotionProduct()
            val responseProduct = apiService.getDetailOfficialStoreOrder(
                page = position, limit = 10, order = orderBy, sort = sort, officialStoreIdL = officialStoreId
            )

            val responseProductArrayList = responseProduct as ArrayList<ProductListDetailDataModel>


            val dataSudahDiTransform = ProductListPromotionProductDataModel.transforms(
                responseProductArrayList, responseSale, responseStock[0].productDetails ?: arrayListOf()
            )

            toLoadResult(
                dataSudahDiTransform,
                nextKey = if (responseProduct.isEmpty()) null else position + 1
            )
        }catch (e: Exception) {
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