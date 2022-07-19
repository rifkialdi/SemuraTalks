package com.example.semuratalks.api

import com.google.gson.annotations.SerializedName

data class ResponseNewsCategory(
    @SerializedName("data")
    val data: DataItem,
)

data class DataItem(
    @SerializedName("description")
    val description: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("posts")
    val posts: List<PostItem>,
)

data class PostItem(
    @SerializedName("link")
    val link: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("thumbnail")
    val thumbnail: String,
)