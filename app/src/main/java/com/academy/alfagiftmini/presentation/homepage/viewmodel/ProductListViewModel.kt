package com.academy.alfagiftmini.presentation.homepage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.domain.produklist.ProductListDomainUseCase
import com.academy.alfagiftmini.domain.produklist.model.ProductListDomainItemModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductListViewModel @Inject constructor(private val useCase: ProductListDomainUseCase) :
    ViewModel() {

    suspend fun getAllProductList(
        type:Int
    ): Flow<PagingData<ProductListDomainItemModel>> {
        return useCase.getAllProduct(viewModelScope,type)
    }

}