package com.example.musicmax.data.model


import java.io.Serializable

data class Album(
    val id: String,
    val title: String,
    val artist: String,
    val description: String,
    val image: String
) : Serializable
