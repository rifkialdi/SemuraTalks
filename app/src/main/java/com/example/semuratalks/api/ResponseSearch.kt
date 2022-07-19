package com.example.semuratalks.api

import com.google.gson.annotations.SerializedName

data class Articles(
	@field:SerializedName("articles")
	val articlesItem: List<ArticlesItem>
)

data class ArticlesItem(
	@field:SerializedName("source")
	val sourceItem: SourceItem,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("urlToImage")
	val urlToImage: String,
)

data class SourceItem(
	@field:SerializedName("name")
	val name: String
)

