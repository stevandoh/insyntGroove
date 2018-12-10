package com.johnny.insytgroove.services

object UtilsApi {
    val BASE_URL_API = "https://jsonplaceholder.typicode.com/"

    val apiService: BaseApiService
        get() = RetrofitClient.getClient(BASE_URL_API).create(BaseApiService::class.java)
}
