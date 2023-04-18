package com.academy.alfagiftmini.data.repository.repositoryimpl

import com.academy.alfagiftmini.data.repository.netwok.loginlogout.LoginApiService
import com.academy.alfagiftmini.data.repository.netwok.officialstore.OfficialStoreApiService
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainRepository
import com.academy.alfagiftmini.domain.officialstore.OfficialStoreDomainRepository
import javax.inject.Inject

class OfficialStoreRepositoryImpl @Inject constructor(
    private val ApiService: OfficialStoreApiService,
) : OfficialStoreDomainRepository {
}