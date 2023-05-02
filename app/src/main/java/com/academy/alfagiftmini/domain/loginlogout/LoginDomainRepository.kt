package com.academy.alfagiftmini.domain.loginlogout

import kotlinx.coroutines.flow.Flow

interface LoginDomainRepository {
    fun login(user: LoginDataDomain): Flow<LoginResponseDomain>
}