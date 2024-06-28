package com.depth.diebingsu.data.remote.model

data class IngredientDTO(
    val check: Boolean,
    val information: Information
)

data class Information(
    val item1: String,
    val item2: String,
    val item3: String
)
