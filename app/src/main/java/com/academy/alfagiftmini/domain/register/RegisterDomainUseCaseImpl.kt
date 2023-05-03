package com.academy.alfagiftmini.domain.register

import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterDomainUseCaseImpl @Inject constructor(private val repository: RegisterDomainRepository) :
    RegisterDomainUseCase {
    override fun register(newUser: RegisterDataDomain): Flow<RegisterResponseDomain> {
        return repository.register(newUser)
    }
}