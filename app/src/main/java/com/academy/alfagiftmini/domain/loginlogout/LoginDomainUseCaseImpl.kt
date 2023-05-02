package com.academy.alfagiftmini.domain.loginlogout

import com.academy.alfagiftmini.domain.loginlogout.LoginDomainRepository
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginDomainUseCaseImpl @Inject constructor(private val repository: LoginDomainRepository) :
    LoginDomainUseCase {
    override fun login(user: LoginDataDomain): Flow<LoginResponseDomain> {
        return repository.login(user)
    }
}