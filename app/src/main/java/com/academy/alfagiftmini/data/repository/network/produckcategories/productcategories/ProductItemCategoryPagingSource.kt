package com.academy.alfagiftmini.data.repository.network.produckcategories.productcategories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.academy.alfagiftmini.data.repository.network.produklist.ProductListApiService
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListDetailDataModel
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListPromotionProductDataModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel

class ProductItemCategoryPagingSource(private val apiService: ProductListApiService, private val subCategory: String, private val category: String): PagingSource<Int, ProductListPromotionProductDomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, ProductListPromotionProductDomainModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductListPromotionProductDomainModel> {
        val position = params.key ?: 1
        return try {
            val responseProduct = apiService.getProductByCategories(
                page = position,
                subCategory = subCategory,
                category = category
            )
            val responseSale = apiService.getPromotionProduct()
            val responseStock = apiService.getProductStock()

            val dataProduct: ArrayList<ProductListDetailDataModel> = responseProduct

            val dataSudahDiTransform = ProductListPromotionProductDataModel.transforms(
                dataProduct, responseSale, responseStock[0].productDetails ?: arrayListOf()
            )

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