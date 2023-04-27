package com.academy.alfagiftmini.domain.register

data class RegisterDataDomain(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val phonenumber: String,
    val memberId: String
)