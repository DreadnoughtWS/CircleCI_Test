package com.academy.alfagiftmini.domain.productcategories

import androidx.paging.PagingData
import com.academy.alfagiftmini.data.repository.repositoryimpl.ProductCategoriesRepositoryImpl
import com.academy.alfagiftmini.domain.productcategories.model.ProductCategoriesDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductCategoriesUseCaseImpl @Inject constructor(private val repository: ProductCategoriesRepository): ProductCategoriesUseCase {
    override suspend fun getAllCategories(scope: CoroutineScope): Flow<PagingData<ProductCategoriesDomainModel>> {
        return repository.getAllCategories(scope)
    }
}