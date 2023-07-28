package com.academy.alfagiftmini.data.repository.repositoryimpl

import com.academy.alfagiftmini.data.repository.network.loginlogout.LoginApiService
import com.academy.alfagiftmini.presentation.authentication.viewmodel.login.LoginDummyData.initValidLoginData
import com.academy.alfagiftmini.presentation.authentication.viewmodel.login.LoginRepoDummy
import com.academy.alfagiftmini.presentation.authentication.viewmodel.login.LoginRepoDummy.initDefaultLoginDataModel
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class LoginRepositoryImplTest{
    @Mock
    lateinit var loginApiService: LoginApiService
    private lateinit var repo: LoginRepositoryImpl

    @Before
    fun setup() {
        repo = LoginRepositoryImpl(loginApiService)
    }

    @Test
    fun `valid data test`() = runTest {
        val data = initDefaultLoginDataModel()
        val param = initValidLoginData()
        val loginOutput = LoginRepoDummy.initDefaultDataLoginResp()
        val expected = LoginRepoDummy.initDefaultDomainLoginResp()
        Mockito.`when`(loginApiService.login(body= data)).thenReturn(Response.success(loginOutput))
        repo.login(param).collectLatest {
            Truth.assertThat(it.user.id).isEqualTo(expected.user.id)
        }
    }

    @Test
    fun `get user data`() = runTest {
        val expected = LoginRepoDummy.initDefaultRegData()
        val id = 0
        val output = LoginRepoDummy.initValidRegData()
        Mockito.`when`(loginApiService.getUserData(0)).thenReturn(output)
        repo.getUserData(id).collectLatest {
            Truth.assertThat(it).isEqualTo(expected)
        }
    }
}