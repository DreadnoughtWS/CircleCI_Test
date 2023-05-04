package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import android.content.Intent
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.productcategories.ProductCategoriesUseCase
import com.academy.alfagiftmini.domain.productcategories.model.ProductCategoriesDomainModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductCategoriesViewModel @Inject constructor(private val useCase: ProductCategoriesUseCase): ViewModel() {
    suspend fun getAllCategories(scope: CoroutineScope): Flow<PagingData<ProductCategoriesDomainModel>> {
        return useCase.getAllCategories(scope)
    }

    fun getIntentData(intent: Intent): ProductCategoriesDomainModel? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(PresentationUtils.CATEGORIES_KEY, ProductCategoriesDomainModel::class.java)
        } else intent.getParcelableExtra(PresentationUtils.CATEGORIES_KEY)
    }

    suspend fun getProductByCategory(scope: CoroutineScope, subCategory: String, category: String): Flow<PagingData<ProductListDomainItemModel>> {
        return useCase.getProductByCategory(scope, subCategory, category)
    }
}