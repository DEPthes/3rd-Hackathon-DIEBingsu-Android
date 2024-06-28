package com.depth.diebingsu.domain.repository

import android.util.Log
import com.depth.diebingsu.core.util.LoggerUtils
import com.depth.diebingsu.data.remote.RetrofitClient
import com.depth.diebingsu.data.remote.Service
import com.depth.diebingsu.data.remote.model.Information
import com.depth.diebingsu.entity.BingsuImage
import com.depth.diebingsu.entity.RandomIngredient

class RepositoryImpl: Repository {
    private val service = RetrofitClient.getInstance().create(Service::class.java)
    override suspend fun getRandomIngredients(): Result<RandomIngredient> {
        return try {
            val res = service.getRandomIngredients()

            if(res.isSuccessful){
                val data = res.body()
                if(data != null){
                    val ingredientEntities = data.information.run { RandomIngredient(item1 = item1, item2 = item2, item3 = item3) }
                    Result.success(ingredientEntities)
                } else {
                    Result.failure(Exception("Received null data"))
                }
            } else {
                Result.failure(Exception("실패"))
            }
        } catch (e: Exception){
            LoggerUtils.e(e.printStackTrace().toString())
            Result.failure(Exception("런타임 에러"))
        }
    }

    override suspend fun getGenerate(information: Information): Result<BingsuImage> {
        return try {
            val res = service.getGenerate(information = information)

            if(res.isSuccessful){
                val data = res.body()
                if(data != null){
                    val ingredientEntities = data.run { BingsuImage(data.information) }
                    Result.success(ingredientEntities)
                } else {
                    Result.failure(Exception("Received null data"))
                }
            } else {
                Result.failure(Exception("실패"))
            }
        } catch (e: Exception){
            LoggerUtils.e(e.printStackTrace().toString())
            Result.failure(Exception("런타임 에러"))
        }
    }


}