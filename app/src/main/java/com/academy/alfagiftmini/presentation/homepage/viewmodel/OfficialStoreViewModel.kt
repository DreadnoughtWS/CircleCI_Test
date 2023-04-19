package com.academy.alfagiftmini.presentation.homepage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import com.academy.alfagiftmini.domain.officialstore.model.OfficialStoreDomainItemModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class OfficialStoreViewModel @Inject constructor(private val useCase: OfficialStoreDomainUseCase) :
    ViewModel() {
    private var _14OfficialStore = MutableLiveData<List<OfficialStoreDomainItemModel>>()
    val officialStore14: MutableLiveData<List<OfficialStoreDomainItemModel>> = _14OfficialStore

    fun get14OfficialStre() {
        viewModelScope.launch {
            useCase.get14OfficialStore().collect {
                _14OfficialStore.postValue(it)
            }
        }
    }

}