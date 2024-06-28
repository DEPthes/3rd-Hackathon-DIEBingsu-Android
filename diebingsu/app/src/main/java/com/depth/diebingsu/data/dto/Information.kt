package com.depth.diebingsu.data.dto

import com.google.gson.annotations.SerializedName

data class Information(
    @SerializedName("boardId")
    val boardId: Int,
    @SerializedName("shavedIceName")
    val shavedIceName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("reaction1")
    val reaction1: Int,
    @SerializedName("reaction2")
    val reaction2: Int,
    @SerializedName("reaction3")
    val reaction3: Int,
    @SerializedName("reaction4")
    val reaction4: Int,
    @SerializedName("reaction5")
    val reaction5: Int,
    @SerializedName("createdAt")
    val createdAt: String
)