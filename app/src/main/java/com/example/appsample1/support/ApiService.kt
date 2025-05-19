package com.example.appsample1.support

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    // GET - Get Config
    @GET("channel/config/{clientId}/{platform}")
    suspend fun getConfig(
        @Path("clientId") clientId: String,
        @Path("platform") platform: String,
    ): Response<GetConfigResponseModel>
}