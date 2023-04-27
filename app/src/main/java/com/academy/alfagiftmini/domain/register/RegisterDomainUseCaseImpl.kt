package com.academy.alfagiftmini.domain.register

import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCase
import javax.inject.Inject

class RegisterDomainUseCaseImpl @Inject constructor(private val repository: RegisterDomainRepository) :
    RegisterDomainUseCase {}