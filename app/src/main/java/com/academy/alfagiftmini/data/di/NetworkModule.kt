package com.academy.alfagiftmini.data.di

import com.academy.alfagiftmini.data.DataUtils.BASE_URL
import com.academy.alfagiftmini.data.repository.netwok.loginlogout.LoginApiService
import com.academy.alfagiftmini.data.repository.netwok.officialstore.OfficialStoreApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    private fun retrofitClient(): Retrofit {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun loginApiService(): LoginApiService {
        return retrofitClient().create(LoginApiService::class.java)
    }
    @Provides
    fun officialStoreApiService(): OfficialStoreApiService {
        return retrofitClient().create(OfficialStoreApiService::class.java)
    }


}