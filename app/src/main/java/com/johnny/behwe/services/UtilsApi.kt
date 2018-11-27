package com.johnny.behwe.services

import com.johnny.behwe.AppController

object UtilsApi {
    val BASE_URL_API = "http://209.97.186.177:3000/v1/"

    val apiService: BaseApiService
        get() = RetrofitClient.getClient(BASE_URL_API).create(BaseApiService::class.java)
}
