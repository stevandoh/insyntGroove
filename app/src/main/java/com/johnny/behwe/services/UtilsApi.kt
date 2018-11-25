package com.johnny.behwe.services

import com.johnny.behwe.AppController

object UtilsApi {
    val BASE_URL_API = AppController.BASE_API

    val apiService: BaseApiService
        get() = RetrofitClient.getClient(BASE_URL_API).create(BaseApiService::class.java)
}
