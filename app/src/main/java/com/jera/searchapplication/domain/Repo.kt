package com.jera.searchapplication.domain

import com.jera.searchapplication.data.model.Track
import com.jera.searchapplication.valueobject.Resource

interface Repo {

    suspend fun getTracks( artistName: String): Resource<List<Track>>
}