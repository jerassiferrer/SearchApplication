package com.jera.searchapplication.domain

import com.jera.searchapplication.data.DataSource
import com.jera.searchapplication.data.model.Track
import com.jera.searchapplication.valueobject.Resource

class RepoImpl(private val dataSource : DataSource): Repo {

    override suspend fun getTracks(artistName: String): Resource<List<Track>> {

        return dataSource.getTracksByArtistName(artistName)
    }
}