package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.domain.akun.AkunDomainUseCase
import com.academy.alfagiftmini.domain.akun.AkunResponseDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AkunViewModel @Inject constructor(private val useCase: AkunDomainUseCase): ViewModel() {
    fun getAkunData(id: Int): Flow<AkunResponseDomain> {
        return useCase.getAkunDetail(id)
    }

    fun deleteAkunData(id: Int){
        useCase.deleteAkun(id)
    }

    fun updateAkunData(id: Int){
        useCase.updateAkun(id)
    }
}