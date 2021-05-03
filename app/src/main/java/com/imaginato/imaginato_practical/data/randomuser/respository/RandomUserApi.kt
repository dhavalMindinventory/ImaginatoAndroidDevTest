package com.imaginato.imaginato_practical.data.randomuser.respository

import com.imaginato.imaginato_practical.data.randomuser.entity.RandomUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {

    @GET("api?results=10")
    suspend fun fetchRandomUsers(@Query("page") page: Int): RandomUserResponse
}
