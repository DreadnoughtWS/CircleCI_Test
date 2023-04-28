package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStorebrandsDomainItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class OfficialStoreViewModel @Inject constructor(private val useCase: OfficialStoreDomainUseCase) :
    ViewModel() {
    private var _14OfficialStore = MutableLiveData<List<OfficialStoreDomainItemModel>>()
    val officialStore14: MutableLiveData<List<OfficialStoreDomainItemModel>> = _14OfficialStore

    private var _brand = MutableLiveData<List<OfficialStorebrandsDomainItemModel>>()
    val brand: MutableLiveData<List<OfficialStorebrandsDomainItemModel>> = _brand

    fun get14OfficialStre() {
        viewModelScope.launch {
            useCase.get14OfficialStore().collect {
                _14OfficialStore.postValue(it)
            }
        }
    }

    suspend fun getAllOfficialStore(): Flow<PagingData<OfficialStoreDomainItemModel>> {
        return useCase.getAllOfficialStore(viewModelScope)
    }

    fun getBrands(id:String){
        viewModelScope.launch {
            useCase.getBrands(id).collect{
                _brand.postValue(it)
            }
        }
    }



}