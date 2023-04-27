package com.academy.alfagiftmini.data.repository.repositoryimpl

import com.academy.alfagiftmini.data.repository.network.register.RegisterApiService
import com.academy.alfagiftmini.domain.register.RegisterDomainRepository
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(private val registerApiService: RegisterApiService) :
    RegisterDomainRepository {
}