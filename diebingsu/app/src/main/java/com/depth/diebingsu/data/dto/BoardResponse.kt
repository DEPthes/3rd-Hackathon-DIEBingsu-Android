package com.depth.diebingsu.data.dto

import com.google.gson.annotations.SerializedName

data class BoardResponse(
    @SerializedName("check")
    val check: Boolean,
    @SerializedName("information")
    val information: List<Information>
)