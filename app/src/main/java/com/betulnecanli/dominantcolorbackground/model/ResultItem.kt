package com.betulnecanli.dominantcolorbackground.model


import com.google.gson.annotations.SerializedName

data class ResultItem(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)