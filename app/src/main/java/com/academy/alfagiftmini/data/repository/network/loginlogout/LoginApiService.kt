package com.academy.alfagiftmini.data.repository.network.loginlogout

import com.academy.alfagiftmini.data.DataUtils
import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginDataModel
import com.academy.alfagiftmini.data.repository.network.loginlogout.model.LoginResponseModel
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApiService {
    @Headers("Content-Type: application/json")
    @POST("login")
    suspend fun login(
        @Body body: RequestBody
    ): LoginResponseModel
}