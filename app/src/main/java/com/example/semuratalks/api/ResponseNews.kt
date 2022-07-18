package com.example.semuratalks.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseNews(
	@field:SerializedName("endpoints")
	val endpoints: List<EndpointsItem>
)

@Parcelize
data class EndpointsItem(
	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("paths")
	val paths: List<PathsItem>
) : Parcelable

@Parcelize
data class PathsItem(
	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("path")
	val path: String
) : Parcelable

