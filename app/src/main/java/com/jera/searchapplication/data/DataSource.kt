package com.jera.searchapplication.data

import android.util.Log
import com.jera.searchapplication.data.model.Track
import com.jera.searchapplication.valueobject.Resource
import com.jera.searchapplication.valueobject.RetrofitClient

class DataSource {

    suspend fun getTracksByArtistName(artistName: String): Resource<List<Track>>{
        val result =RetrofitClient.webService.getTracksByArtistName(artistName)
        Log.d("getTracksByArtistName","count:${result.resultCount}   "+"${result.trackList}")
        return Resource.Success(result.trackList)
    }


}