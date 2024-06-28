package com.depth.diebingsu.data.remote

import com.depth.diebingsu.data.remote.model.IngredientDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("/ingredient")
    suspend fun getRandomIngredient(
        @Query("count") count: Int
    ): Response<IngredientDTO>
}