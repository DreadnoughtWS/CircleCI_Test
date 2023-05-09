package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.academy.alfagiftmini.domain.banner.BannerDomainUseCase
import com.academy.alfagiftmini.domain.banner.model.BannerDomainModel
import com.academy.alfagiftmini.domain.banner.model.BannerResponseModel
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class BannerListViewModel @Inject constructor(private val useCase: BannerDomainUseCase) :
    ViewModel() {

    private val _bannerListData= MutableStateFlow<List<BannerDomainModel>>(listOf())
    val bannerListData : StateFlow<List<BannerDomainModel>> =  _bannerListData

    fun getAllBannerList(){
        CoroutineScope(IO).launch{
            useCase.getallBanners().collectLatest {
                _bannerListData.value=it
            }
        }
    }


}