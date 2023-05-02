package com.academy.alfagiftmini.domain.loginlogout

import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginResponseModel
import kotlinx.coroutines.flow.Flow

interface LoginDomainUseCase {
    fun login(user: LoginDataDomain): Flow<LoginResponseDomain>
}