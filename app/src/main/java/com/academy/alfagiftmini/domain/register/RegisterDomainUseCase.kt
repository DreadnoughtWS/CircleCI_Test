package com.academy.alfagiftmini.domain.register

import kotlinx.coroutines.flow.Flow

interface RegisterDomainUseCase {
    fun register(newUser: RegisterDataDomain): Flow<RegisterResponseDomain>
    fun checkAvailableEmail(email: String): Flow<RegisterResponseDomain>
}