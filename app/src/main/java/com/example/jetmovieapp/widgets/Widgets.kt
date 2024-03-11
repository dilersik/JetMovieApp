package com.example.jetmovieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.jetmovieapp.model.Movie
import com.example.jetmovieapp.model.getMovies

@Preview
@Composable
fun MovieRow(movie: Movie = getMovies().first(), onClick: (String) -> Unit = {}) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { onClick(movie.id) },
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                color = Color.White
            ) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images.first())
                        .crossfade(true)
                        .transformations(CircleCropTransformation())
                        .build()
                )
                Image(
                    painter = painter,
                    contentDescription = movie.title
                )
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.headlineSmall)
                Text(text = "Genre: ${movie.genre}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Released: ${movie.year}", style = MaterialTheme.typography.bodyMedium)

                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)) {
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.Gray, fontSize = 13.sp)) {
                                append(movie.plot)
                            }
                        }, lineHeight = 16.sp)

                        Divider(modifier = Modifier.padding(8.dp))

                        Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.bodyMedium)
                    }
                }

                Icon(
                    modifier = Modifier
                        .size(25.dp)
                        .clickable { expanded = !expanded },
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    tint = Color.DarkGray
                )
            }
        }
    }
}