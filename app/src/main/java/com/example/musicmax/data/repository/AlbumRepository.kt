package com.example.musicmax.data.repository


import com.example.musicmax.data.model.Album
import com.example.musicmax.data.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AlbumRepository {
    private val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://music.juanfrausto.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    suspend fun getAlbums(): List<Album> = api.getAlbums()

    suspend fun getAlbumDetail(id: String): Album = api.getAlbumDetail(id)
}
