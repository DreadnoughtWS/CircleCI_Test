package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.productcategories.ProductCategoriesUseCase
import com.academy.alfagiftmini.domain.productcategories.model.ProductCategoriesDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductCategoriesViewModel @Inject constructor(private val useCase: ProductCategoriesUseCase): ViewModel() {
    suspend fun getAllCategories(scope: CoroutineScope): Flow<PagingData<ProductCategoriesDomainModel>> {
        return useCase.getAllCategories(scope)
    }
}