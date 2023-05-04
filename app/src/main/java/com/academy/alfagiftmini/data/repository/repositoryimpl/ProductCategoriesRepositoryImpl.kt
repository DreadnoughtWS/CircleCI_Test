package com.academy.alfagiftmini.data.repository.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.academy.alfagiftmini.data.repository.network.produckcategories.ProductCategoriesApiService
import com.academy.alfagiftmini.data.repository.network.produckcategories.productcategories.ProductCategoriesPagingSource
import com.academy.alfagiftmini.data.repository.network.produckcategories.productcategories.ProductItemCategoryPagingSource
import com.academy.alfagiftmini.data.repository.network.produklist.ProductListApiService
import com.academy.alfagiftmini.domain.productcategories.ProductCategoriesRepository
import com.academy.alfagiftmini.domain.productcategories.model.ProductCategoriesDomainModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductCategoriesRepositoryImpl @Inject constructor(private var productListApiService: ProductListApiService, private var apiService: ProductCategoriesApiService): ProductCategoriesRepository {
    override suspend fun getAllCategories(scope: CoroutineScope): Flow<PagingData<ProductCategoriesDomainModel>> {
        return Pager(config = PagingConfig(
            pageSize = 10
        )) {
            ProductCategoriesPagingSource(apiService)
        }.flow.cachedIn(scope)
    }

    override suspend fun getProductByCategory(
        scope: CoroutineScope,
        subCategory: String,
        category: String
    ): Flow<PagingData<ProductListPromotionProductDomainModel>> {
        return Pager(config = PagingConfig(
            pageSize = 10
        )) {
            ProductItemCategoryPagingSource(productListApiService, subCategory, category)
        }.flow.cachedIn(scope)
    }

}