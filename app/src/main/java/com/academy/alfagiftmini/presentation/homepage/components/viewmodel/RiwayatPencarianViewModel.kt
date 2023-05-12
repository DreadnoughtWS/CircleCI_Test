package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.academy.alfagiftmini.data.repository.local.riwayatpencarian.model.RiwayatPencarianDataModel
import com.academy.alfagiftmini.domain.riwayatpencarian.RiwayatPencarianUseCase
import com.academy.alfagiftmini.domain.riwayatpencarian.model.RiwayatPencarianDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class RiwayatPencarianViewModel @Inject constructor(private val useCase: RiwayatPencarianUseCase): ViewModel() {
    private val _riwayatLiveData = MutableLiveData<List<RiwayatPencarianDomainModel>>()
    val getRiwayatLiveData: LiveData<List<RiwayatPencarianDomainModel>> = _riwayatLiveData
    init {
        getRiwayatPencarian()
    }

    fun insertRiwayatPencarian(text: String) {
        CoroutineScope(IO).launch {
            useCase.insertRiwayatPencarian(RiwayatPencarianDomainModel(text))
            getRiwayatPencarian()
        }
    }

    private fun getRiwayatPencarian() {
        CoroutineScope(IO).launch {
            useCase.getRiwayatPencarian().collectLatest {
                _riwayatLiveData.postValue(it)
            }
        }
    }

    fun deleteRiwayatPencarian() {
        CoroutineScope(IO).launch {
            useCase.deleteRiwayatPencarian()
            getRiwayatPencarian()
        }
    }
}