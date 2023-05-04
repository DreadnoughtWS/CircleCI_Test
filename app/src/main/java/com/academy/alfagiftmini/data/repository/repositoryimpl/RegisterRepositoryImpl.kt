package com.academy.alfagiftmini.data.repository.repositoryimpl

import com.academy.alfagiftmini.data.repository.network.register.RegisterApiService
import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import com.academy.alfagiftmini.domain.register.RegisterDomainRepository
import com.academy.alfagiftmini.domain.register.RegisterResponseDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(private val registerApiService: RegisterApiService) :
    RegisterDomainRepository {
    override fun register(newUser: RegisterDataDomain): Flow<RegisterResponseDomain> {
        TODO("Not yet implemented")
    }
}