package com.lwg.model.movie

import kotlinx.serialization.Serializable

@Serializable
data class Genre (
    val id: Int,
    val name: String,
    val isSelected: Boolean = false
)
