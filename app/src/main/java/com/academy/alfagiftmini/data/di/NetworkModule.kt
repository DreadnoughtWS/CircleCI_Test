package com.academy.alfagiftmini.data.di

import com.academy.alfagiftmini.data.DataUtils.BASE_URL
import com.academy.alfagiftmini.data.repository.network.loginlogout.LoginApiService
import com.academy.alfagiftmini.data.repository.network.officialstore.OfficialStoreApiService
import com.academy.alfagiftmini.data.repository.network.produklist.ProductListApiService
import com.academy.alfagiftmini.data.repository.network.register.RegisterApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
class NetworkModule {

    private fun retrofitClient(): Retrofit {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            .addInterceptor(Interceptor { chain ->
                val request = chain.request()
                var string = request.url.toString()
                var string2 = request.url.toString()
                string = string.replace("%26", "&")
                string2 = string.replace("%3D", "=")
                val newRequest = Request.Builder().url(string).url(string2).build()
                chain.proceed(newRequest)
            }).build()
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
    }

    @Provides
    fun loginApiService(): LoginApiService {
        return retrofitClient().create(LoginApiService::class.java)
    }

    @Provides
    fun registerApiService(): RegisterApiService {
        return retrofitClient().create(RegisterApiService::class.java)
    }

    @Provides
    fun officialStoreApiService(): OfficialStoreApiService {
        return retrofitClient().create(OfficialStoreApiService::class.java)
    }

    @Provides
    fun productListApiService(): ProductListApiService {
        return retrofitClient().create(ProductListApiService::class.java)
    }


}