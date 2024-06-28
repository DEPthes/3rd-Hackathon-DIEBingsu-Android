package com.depth.diebingsu.data.repository

import android.content.Context
import android.provider.Settings
import android.util.Log
import com.depth.diebingsu.data.dto.Information
import com.depth.diebingsu.data.remote.BoardService
import com.depth.diebingsu.data.remote.RetrofitClient
import org.json.JSONObject

class BoardRepositoryImpl : BoardRepository {
    private val service = RetrofitClient.getInstance().create(BoardService::class.java)
    override suspend fun getBoard(deviceId: String): Result<List<Information>> {
        val res = service.getBoard(deviceId)

        return if (res.isSuccessful) {
            if (res.body() == null) {
                Result.success(listOf())
            }
            else {
                Result.success(res.body()!!.information)
            }
        }
        else {
            val errorMsg = JSONObject(res.errorBody()!!.string()).getString("msg")
            Result.failure(java.lang.Exception(errorMsg))
        }
    }
}