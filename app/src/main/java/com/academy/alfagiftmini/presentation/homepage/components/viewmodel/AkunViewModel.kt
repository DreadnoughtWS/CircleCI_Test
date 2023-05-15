package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.lifecycle.ViewModel
import com.academy.alfagiftmini.domain.akun.AkunDomainDataModel
import com.academy.alfagiftmini.domain.akun.AkunDomainEditDataModel
import com.academy.alfagiftmini.domain.akun.AkunDomainUseCase
import com.academy.alfagiftmini.domain.akun.AkunResponseDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AkunViewModel @Inject constructor(private val useCase: AkunDomainUseCase): ViewModel() {
    fun getAkunData(id: Int): Flow<AkunResponseDomain> {
        return useCase.getAkunDetail(id)
    }

    suspend fun updateAkunData(editedAkunData: AkunDomainEditDataModel, id: Int){
        withContext(Dispatchers.IO){
            useCase.updateAkun(editedAkunData, id)
        }
    }
}