package com.example.semuratalks.api

import com.google.gson.annotations.SerializedName
// belum kepake
data class ResponseNews(
	@field:SerializedName("endpoints")
	val endpoints: List<EndpointsItem>
)

data class EndpointsItem(
	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("paths")
	val paths: List<PathsItem>
)

data class PathsItem(
	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("path")
	val path: String
)

