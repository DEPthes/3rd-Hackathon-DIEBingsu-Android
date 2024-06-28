package com.depth.diebingsu.data.remote

import com.depth.diebingsu.data.remote.model.GenerateDTO
import com.depth.diebingsu.data.remote.model.Information
import com.depth.diebingsu.data.remote.model.IngredientDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET


interface Service {
    @GET("/api/v1/ingredient")
    suspend fun getRandomIngredients(): Response<IngredientDTO>
    @GET("/api/v1/user/generate")
    suspend fun getGenerate(@Body information: Information): Response<GenerateDTO>
}