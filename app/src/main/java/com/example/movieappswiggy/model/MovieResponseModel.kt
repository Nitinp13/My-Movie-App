package com.example.movieappswiggy.model


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponseModel(
    @SerializedName("Response")
    val response: String? = null,
    @SerializedName("Search")
    val search: List<MovieItem?>? = null,
    @SerializedName("totalResults")
    val totalResults: String? = null
) {
    @Serializable
    data class MovieItem(
        @SerializedName("imdbID")
        val imdbID: String? = null,
        @SerializedName("Poster")
        val poster: String? = null,
        @SerializedName("Title")
        val title: String? = null,
        @SerializedName("Type")
        val type: String? = null,
        @SerializedName("Year")
        val year: String? = null
    )
}