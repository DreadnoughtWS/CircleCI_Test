package com.academy.alfagiftmini.domain.productcategories

import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.productcategories.model.ProductCategoriesDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ProductCategoriesRepository {
    suspend fun getAllCategories(scope: CoroutineScope): Flow<PagingData<ProductCategoriesDomainModel>>
}