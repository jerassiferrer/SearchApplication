package com.jera.searchapplication.data.model

import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("artistName")
    val artistName: String ="",
    @SerializedName("trackName")
    val trackName: String ="",
    @SerializedName("releaseDate")
    val releaseDate: String ="",
    @SerializedName("primaryGenreName")
    val primaryGenreName: String ="",
    @SerializedName("trackPrice")
    val trackPrice: Float = 0.00f
                    ){}

data class TrackListResult(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val trackList : List<Track>
)


