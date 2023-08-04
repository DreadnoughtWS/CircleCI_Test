package com.academy.alfagiftmini.presentation.authentication.viewmodel

import com.academy.alfagiftmini.data.di.NetworkModule
import com.academy.alfagiftmini.data.repository.network.loginlogout.LoginApiService
import com.academy.alfagiftmini.data.repository.repositoryimpl.LoginRepositoryImpl
import com.academy.alfagiftmini.domain.loginlogout.LoginDataDomain
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainRepository
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCase
import com.academy.alfagiftmini.domain.loginlogout.LoginDomainUseCaseImpl
import com.academy.alfagiftmini.presentation.authentication.viewmodel.login.LoginDummyData
import com.google.common.truth.Truth
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {
    private lateinit var dummyData: LoginDataDomain
    private lateinit var loginViewModel: LoginViewModel

    @Mock
    lateinit var usecase: LoginDomainUseCaseImpl
    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(usecase)
    }

    @Test
    fun `return false if email is empty`() {
        dummyData = LoginDummyData.initEmptyEmailLoginData()
        val result = loginViewModel.checkUserInputValidity(dummyData)
        Truth.assertThat(result.first).isEqualTo("email")
        Truth.assertThat(result.second).isFalse()
        Truth.assertThat(result).isNotNull()
    }

    @Test
    fun `return false if password is empty`() {
        dummyData = LoginDummyData.initEmptyPassLoginData()
        val result = loginViewModel.checkUserInputValidity(dummyData)
        Truth.assertThat(result.first).isEqualTo("pass")
        Truth.assertThat(result.second).isFalse()
        Truth.assertThat(result).isNotNull()
    }

    @Test
    fun `return true if both is not empty`() {
        dummyData = LoginDummyData.initValidLoginData()
        val result = loginViewModel.checkUserInputValidity(dummyData)
        Truth.assertThat(result.first).isEqualTo("email pass")
        Truth.assertThat(result.second).isTrue()
        Truth.assertThat(result).isNotNull()
    }
}