package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.academy.alfagiftmini.domain.banner.BannerDomainUseCase
import com.academy.alfagiftmini.domain.banner.model.BannerDomainModel
import com.academy.alfagiftmini.domain.banner.model.BannerResponseModel
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class BannerListViewModel @Inject constructor(private val useCase: BannerDomainUseCase) :
    ViewModel() {

    private val _bannerListData= MutableStateFlow(BannerResponseModel(listOf()))
    val bannerListData : StateFlow<BannerResponseModel> =  _bannerListData

    suspend fun getAllBannerList(){
        useCase.getallBanners().collectLatest {
            _bannerListData.value=it
        }
    }


}