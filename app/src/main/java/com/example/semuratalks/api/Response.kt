package com.example.semuratalks.api

import com.google.gson.annotations.SerializedName
// belum kepake
data class Response(
	@field:SerializedName("endpoints")
	val endpoints: List<EndpointsItem>
)

data class EndpointsItem(
	@field:SerializedName("paths")
	val paths: List<PathsItem>,

	@field:SerializedName("name")
	val name: String
)

data class PathsItem(
	@field:SerializedName("path")
	val path: String,

	@field:SerializedName("name")
	val name: String
)

