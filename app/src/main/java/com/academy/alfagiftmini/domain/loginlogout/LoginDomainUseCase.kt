package com.academy.alfagiftmini.domain.loginlogout

import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginResponseModel
import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import kotlinx.coroutines.flow.Flow

interface LoginDomainUseCase {
    fun login(user: LoginDataDomain): Flow<LoginResponseDomain>
    fun getUserData(id: Int): Flow<RegisterDataDomain>
}