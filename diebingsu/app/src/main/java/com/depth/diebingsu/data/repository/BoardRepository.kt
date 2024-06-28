package com.depth.diebingsu.data.repository

import com.depth.diebingsu.data.dto.Information

interface BoardRepository {
    suspend fun getBoard(
        deviceId: String
    ): Result<List<Information>>
}