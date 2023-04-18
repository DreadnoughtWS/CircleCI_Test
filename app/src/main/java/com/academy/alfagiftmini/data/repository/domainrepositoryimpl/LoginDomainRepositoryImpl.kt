package com.academy.alfagiftmini.data.repository.domainrepositoryimpl

import com.academy.alfagiftmini.data.repository.netwok.loginlogout.LoginApiService
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainRepository
import javax.inject.Inject

class LoginDomainRepositoryImpl @Inject constructor(
    private val loginApiService: LoginApiService,
) : LoginDomainRepository {
}