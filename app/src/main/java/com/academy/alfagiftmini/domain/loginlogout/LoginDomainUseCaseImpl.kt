package com.academy.alfagiftmini.domain.loginlogout

import com.academy.alfagiftmini.domain.loginlogout.LoginDomainRepository
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCase
import javax.inject.Inject

class LoginDomainUseCaseImpl @Inject constructor(private val repository: LoginDomainRepository) :
    LoginDomainUseCase {
}