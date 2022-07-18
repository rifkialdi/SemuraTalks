package com.example.semuratalks.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsModel(
    val name: String,
    val path: List<Url>
) : Parcelable

@Parcelize
data class Url(
    val name: String,
    val path: String
) : Parcelable

