package com.academy.alfagiftmini.data.repository.netwok.loginlogout

import com.academy.alfagiftmini.domain.loginlogout.LoginDomainRepository
import javax.inject.Inject

class LoginDomainUseCaseImpl @Inject constructor(private val repository: LoginDomainRepository) :
    LoginDomainUseCase {
}