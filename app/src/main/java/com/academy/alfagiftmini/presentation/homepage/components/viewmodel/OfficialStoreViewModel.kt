package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStorebrandsDomainItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class OfficialStoreViewModel @Inject constructor(private val useCase: OfficialStoreDomainUseCase) :
    ViewModel() {
    private var _14OfficialStore = MutableLiveData<List<OfficialStoreDomainItemModel>>()
    val officialStore14: MutableLiveData<List<OfficialStoreDomainItemModel>> = _14OfficialStore

    private var _officialStore = MutableLiveData<PagingData<OfficialStoreDomainItemModel>>()
    val officialStore: MutableLiveData<PagingData<OfficialStoreDomainItemModel>> = _officialStore

    var _brand = MutableLiveData<List<OfficialStorebrandsDomainItemModel>>()
    val brand: MutableLiveData<List<OfficialStorebrandsDomainItemModel>> = _brand

    fun get14OfficialStre() {
        viewModelScope.launch {
            useCase.get14OfficialStore().collect {
                _14OfficialStore.postValue(it)
            }
        }
    }

    suspend fun getAllOfficialStore(
        name: String, type: String
    ): Flow<PagingData<OfficialStoreDomainItemModel>> {
        viewModelScope.launch {
            useCase.getAllOfficialStore(viewModelScope, name, type).collectLatest {
                _officialStore.postValue(it)
            }
        }
        return useCase.getAllOfficialStore(viewModelScope, name, type)
    }

    fun getBrands(id: String) {
        viewModelScope.launch {
            useCase.getBrands(id).collect {
                _brand.postValue(it)
            }
        }
    }

    val _itemCount = MutableLiveData<Int>()
    val itemCount: LiveData<Int> = _itemCount

    fun setItemCount(itemCount: Int) {
        _itemCount.value = itemCount
    }


}