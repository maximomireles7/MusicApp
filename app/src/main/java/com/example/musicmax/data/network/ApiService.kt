package com.example.musicmax.data.network


import com.example.musicmax.data.model.Album
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("albums/{id}")
    suspend fun getAlbumDetail(@Path("id") id: String): Album
}
