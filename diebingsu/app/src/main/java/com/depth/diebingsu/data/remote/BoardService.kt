package com.depth.diebingsu.data.remote

import com.depth.diebingsu.data.dto.BoardResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BoardService {
    @GET("/api/v1/user")
    suspend fun getBoard(
        @Query("deviceId") deviceId: String
    ): Response<BoardResponse>
}