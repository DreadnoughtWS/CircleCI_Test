package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.domain.banner.BannerDomainUseCase
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainUseCase
import javax.inject.Inject

class BannerListViewModel @Inject constructor(private val useCase: BannerDomainUseCase) :
    ViewModel() {

}