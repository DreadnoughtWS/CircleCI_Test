package com.academy.alfagiftmini.domain.register

data class RegisterDataDomain(
    val email: String?,
    val password: String?,
    val firstName: String?,
    val lastName: String?,
    val phone: String?,
    val memberId: String?
)

data class RegisterResponseDomain (
    val accessToken: String?,
    val user: RegisterDataDomain?,
    val error: String?
)