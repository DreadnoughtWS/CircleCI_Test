package com.academy.alfagiftmini.domain.register

import kotlinx.coroutines.flow.Flow

interface RegisterDomainRepository {
    fun register(newUser: RegisterDataDomain): Flow<RegisterResponseDomain>
}