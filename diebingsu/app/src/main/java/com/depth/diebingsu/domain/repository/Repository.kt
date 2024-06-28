package com.depth.diebingsu.domain.repository

import com.depth.diebingsu.data.remote.model.Information
import com.depth.diebingsu.entity.BingsuImage
import com.depth.diebingsu.entity.RandomIngredient

interface Repository {
    suspend fun getRandomIngredients(): Result<RandomIngredient>

    suspend fun getGenerate(information: Information): Result<BingsuImage>
}