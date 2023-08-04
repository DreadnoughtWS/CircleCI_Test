package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import android.content.Intent
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.productcategories.ProductCategoriesUseCase
import com.academy.alfagiftmini.domain.productcategories.model.ProductCategoriesDomainModel
import com.academy.alfagiftmini.domain.produklist.model.ProductListPromotionProductDomainModel
import com.academy.alfagiftmini.presentation.PresentationUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class ProductCategoriesViewModel @Inject constructor(private val useCase: ProductCategoriesUseCase): ViewModel() {
    private val pagingData =  MutableLiveData<PagingData<ProductCategoriesDomainModel>>()
    var liveData: LiveData<PagingData<ProductCategoriesDomainModel>> = pagingData
    suspend fun getAllCategories(scope: CoroutineScope, limit: Int?) {
        useCase.getAllCategories(scope, limit).collectLatest {
            pagingData.postValue(it)
        }
    }

    fun getIntentData(intent: Intent): ProductCategoriesDomainModel? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(PresentationUtils.CATEGORIES_KEY, ProductCategoriesDomainModel::class.java)
        } else intent.getParcelableExtra(PresentationUtils.CATEGORIES_KEY)
    }
}