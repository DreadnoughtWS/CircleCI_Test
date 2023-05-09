package com.academy.alfagiftmini.domain.loginlogout

import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import kotlinx.coroutines.flow.Flow

interface LoginDomainRepository {
    fun login(user: LoginDataDomain): Flow<LoginResponseDomain>
    fun getUserData(id: Int): Flow<RegisterDataDomain>
}