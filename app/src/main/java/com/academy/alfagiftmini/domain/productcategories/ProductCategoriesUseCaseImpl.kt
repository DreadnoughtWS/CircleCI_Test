package com.academy.alfagiftmini.domain.productcategories

import androidx.paging.PagingData
import com.academy.alfagiftmini.data.repository.network.produklist.model.ProductListPromotionProductDataModel
import com.academy.alfagiftmini.domain.productcategories.model.ProductCategoriesDomainModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductCategoriesUseCaseImpl @Inject constructor(private val repository: ProductCategoriesRepository): ProductCategoriesUseCase {
    override suspend fun getAllCategories(scope: CoroutineScope, limit: Int?): Flow<PagingData<ProductCategoriesDomainModel>> {
        return repository.getAllCategories(scope, limit)
    }
}