package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.domain.banner.BannerDomainUseCase
import com.academy.alfagiftmini.domain.banner.model.BannerDomainModel
import com.academy.alfagiftmini.domain.banner.model.BannerResponseModel
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class BannerListViewModel @Inject constructor(private val useCase: BannerDomainUseCase) :
    ViewModel() {

    private val _bannerSlider= MutableStateFlow(BannerResponseModel(listOf()))
    val bannerSlider : StateFlow<BannerResponseModel> =  _bannerSlider



}