package com.example.testapp.data

import com.google.gson.annotations.SerializedName

data class EventDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String?,
    @SerializedName("subtitle")
    val subtitle: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("videoUrl")
    val videoUrl: String?,
)