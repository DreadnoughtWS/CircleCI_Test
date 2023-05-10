package com.academy.alfagiftmini.domain.register

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterDomainUseCaseImpl @Inject constructor(private val repository: RegisterDomainRepository) :
    RegisterDomainUseCase {
    override fun register(newUser: RegisterDataDomain): Flow<RegisterResponseDomain> {
        return repository.register(newUser)
    }

    override fun checkAvailableEmail(email: String): Flow<RegisterResponseDomain> {
        return repository.checkAvailableEmail(email)
    }
}