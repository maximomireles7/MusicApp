package com.example.musicmax.ui.detail


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.example.musicmax.data.model.Album
import com.example.musicmax.data.repository.AlbumRepository
import com.example.musicmax.ui.components.MiniPlayer
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(albumId: String, navController: NavController) {
    val repo = AlbumRepository()
    var album by remember { mutableStateOf<Album?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            album = repo.getAlbumDetail(albumId)
        }
    }

    if (album == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { MiniPlayer(currentAlbum = album) }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF10002B))
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 90.dp)
                ) {
                    item { HeaderDetail(album!!, navController) }
                    item { AboutSection(album!!) }
                    item { ArtistSection(album!!) }
                    item { TrackList(album!!) }
                }
            }
        }
    }
}

@Composable
fun HeaderDetail(album: Album, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
    ) {
        AsyncImage(
            model = album.image,
            contentDescription = album.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color(0xFF4B0082).copy(alpha = 0.85f)
                        )
                    )
                )
        )

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = Color.White
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = album.title,
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = album.artist,
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.9f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .background(Color(0xFF4B0082), CircleShape)
                        .size(50.dp)
                ) {
                    Icon(
                        Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .background(Color.White, CircleShape)
                        .size(50.dp)
                ) {
                    Icon(
                        Icons.Default.Pause,
                        contentDescription = "Pause",
                        tint = Color(0xFF4B0082)
                    )
                }
            }
        }
    }
}

@Composable
fun AboutSection(album: Album) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("About this album", fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Text(album.description, color = Color.Gray)
        }
    }
}

@Composable
fun ArtistSection(album: Album) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Artist:",
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        Surface(
            color = Color.White.copy(alpha = 0.2f),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = album.artist,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                fontSize = 12.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun TrackList(album: Album) {
    val tracks = List(10) { "${album.title} • Track ${it + 1}" }

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(16.dp))
        tracks.forEach { track ->
            ListItem(
                headlineContent = { Text(track, color = Color.White) },
                supportingContent = {
                    Text(album.artist, color = Color.LightGray, fontSize = 12.sp)
                },
                leadingContent = {
                    Image(
                        painter = rememberAsyncImagePainter(album.image),
                        contentDescription = album.title,
                        modifier = Modifier.size(50.dp)
                    )
                }
            )
            Divider(color = Color.White.copy(alpha = 0.2f))
        }
    }
}
