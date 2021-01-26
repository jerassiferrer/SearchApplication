package com.jera.searchapplication.domain

import com.jera.searchapplication.data.model.TrackListResult
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("search?")
    suspend fun getTracksByArtistName(@Query("term") artistName:String) : TrackListResult
}