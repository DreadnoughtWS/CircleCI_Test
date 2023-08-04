package com.academy.alfagiftmini.presentation.authentication.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.academy.alfagiftmini.data.repository.network.register.RegisterApiService
import com.academy.alfagiftmini.data.repository.network.register.model.RegisterDataModel
import com.academy.alfagiftmini.data.repository.network.register.model.RegisterResponseModel
import com.academy.alfagiftmini.data.repository.repositoryimpl.RegisterRepositoryImpl
import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import com.academy.alfagiftmini.domain.register.RegisterDomainUseCase
import com.academy.alfagiftmini.domain.register.RegisterDomainUseCaseImpl
import com.academy.alfagiftmini.domain.register.RegisterResponseDomain
import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.util.concurrent.Flow

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class RegisterViewModelTest {

  lateinit var viewModel: RegisterViewModel
  private val testDispatcher = StandardTestDispatcher()
  private lateinit var registerDummy: RegisterDataDomain
  private lateinit var registerDummy2: RegisterDataDomain
  private lateinit var registerDummyModel: RegisterDataModel

//  @Mock
  lateinit var useCaseMock: RegisterDomainUseCase

  @Mock
  lateinit var sharedPrefs: SharedPreferences

  @Mock
  lateinit var activity: AppCompatActivity

  @Mock
  lateinit var application: Application

//  @Mock
  lateinit var registerRepository: RegisterRepositoryImpl

  @Mock
  lateinit var apiService: RegisterApiService

  @get:Rule
  var rule: TestRule = InstantTaskExecutorRule()

  @Before
  fun setup() {
    MockitoAnnotations.openMocks(this)
    Dispatchers.setMain(testDispatcher)
    registerRepository = RegisterRepositoryImpl(apiService)
    useCaseMock = RegisterDomainUseCaseImpl(registerRepository)
    viewModel = RegisterViewModel(useCaseMock)
    Mockito.`when`(activity.application).thenReturn(application)
    Mockito.`when`(application.getSharedPreferences("my_key", 0)).thenReturn(sharedPrefs)
    registerDummy = RegisterDataDomain(
      null,
      "",
      "",
      "",
      "",
      "",
      "",
      listOf()
    )
    registerDummy2 = RegisterDataDomain(
      10,
      "test@gmail.com",
      "passnull",
      "david",
      "sw",
      "+6281249400599",
      "023456789540",
      listOf()
    )
    registerDummyModel = RegisterDataModel(
      10,
      "test@gmail.com",
      "passnull",
      "david",
      "sw",
      "+6281249400599",
      "023456789540",
      listOf()
    )
  }

  @Test
  fun `phoneNumberisString returns string`() {
    var phone = viewModel.phoneNumberFormatted("081249400599")
    assertThat(phone).startsWith("+62")
    phone = viewModel.phoneNumberFormatted("81249400599")
    assertThat(phone).startsWith("+62")
    phone = viewModel.phoneNumberFormatted("49400599")
    assertEquals("+6249400599", phone)
  }

  @Test
  fun `otpCountdownTimer should set _finished LiveData to true when countdown finishes`() =
    runTest {
      withContext(Dispatchers.Default) {
        viewModel.otpCountdownTimer()
      }
      // Wait for the countdown to finish (301000ms + 1000ms for safety)
      assertEquals(true, viewModel.finished.value)
    }

  @Test
  fun `generateOTP returns 6 digit numbers in string`() = runTest {
    withContext(Dispatchers.Default) {
      viewModel.generateOTP()
    }
    assertEquals(6, viewModel.otp.length)
  }

  @Test
  fun `checkPhoneLength returns true`() {
    var phoneLengthCheck = viewModel.checkPhoneLength("081249400599")
    assertEquals(true, phoneLengthCheck)
    phoneLengthCheck = viewModel.checkPhoneLength("81249400599")
    assertEquals(true, phoneLengthCheck)
    phoneLengthCheck = viewModel.checkPhoneLength("49400599")
    assertEquals(false, phoneLengthCheck)
  }

  @Test
  fun `checkAvailableEmail returns response flow`() {
  }

  @Test
  fun `registerNewUser returns response flow`() = runTest{
    Mockito.`when`(apiService.register(body = registerDummyModel))
      .thenReturn(Response.success(RegisterResponseModel(null, null, "success")))
    viewModel.registerNewUser(registerDummy).collectLatest {
      assertEquals("error", it.error)
    }
    viewModel.registerNewUser(registerDummy2).collectLatest {
      assertEquals(RegisterResponseDomain(null,registerDummy,null), it)
    }
  }
}
