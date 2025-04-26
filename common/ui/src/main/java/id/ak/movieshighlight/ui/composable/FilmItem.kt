package id.ak.movieshighlight.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import id.ak.convention.common.ui.R

@Composable
fun FilmItem(posterUrl: String, title: String, modifier: Modifier = Modifier) {
    AsyncImage(
        modifier = modifier,
        model = posterUrl,
        contentDescription = title,
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.baseline_image_24)
    )
}