package com.academy.alfagiftmini.domain.loginlogout

import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginResponseModel
import com.academy.alfagiftmini.domain.register.RegisterDataDomain
import com.academy.alfagiftmini.presentation.authentication.viewmodel.login.LoginDummyData
import com.academy.alfagiftmini.presentation.authentication.viewmodel.login.LoginRepoDummy
import com.google.common.truth.Truth
import org.junit.Test

class LoginResponseTransformTest {
    private lateinit var dummyData: LoginResponseModel
    @Test
    fun `if authtoken null` () {
        dummyData = LoginRepoDummy.initLoginResponse(
            null,
            LoginDummyData.initRegData(),
            "error"
        )
        val result = LoginResponseModel.transform(dummyData)
        Truth.assertThat(result.accessToken).isEqualTo("")
        Truth.assertThat(result.user).isEqualTo(RegisterDataDomain(null, "", "", "", "", "",  "",  listOf()))
        Truth.assertThat(result.error).isEqualTo(dummyData.error)
    }

    @Test
    fun `if register is null`() {
        dummyData = LoginRepoDummy.initLoginResponse(
            "sidjfiosdbf",
            null,
            "error"
        )
        val result = LoginResponseModel.transform(dummyData)
        Truth.assertThat(result.accessToken).isEqualTo(dummyData.accessToken)
        Truth.assertThat(result.user).isEqualTo(
            RegisterDataDomain(null,
                "",
                "",
                "",
                "",
                "",
                "",
                listOf()
            )
        )
        Truth.assertThat(result.error).isEqualTo(dummyData.error)
    }

    @Test
    fun `if error is null`() {
        dummyData = LoginRepoDummy.initLoginResponse(
            "1",
            LoginDummyData.initRegData(),
            null
        )
        val result = LoginResponseModel.transform(dummyData)
        Truth.assertThat(result.accessToken).isEqualTo(dummyData.accessToken)
        Truth.assertThat(result.user).isEqualTo(RegisterDataDomain(null, "", "", "", "", "",  "",  listOf()))
        Truth.assertThat(result.error).isEqualTo("")
    }
}