package id.ak.movieshighlight.watchlist.sheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import id.ak.convention.features.details.R
import id.ak.movieshighlight.core.ext.formatCurrency
import id.ak.movieshighlight.core.ext.formatLocalNumber
import id.ak.movieshighlight.domain.entity.Movie
import java.util.Locale

@Composable
fun MovieSheet(movie: Movie, modifier: Modifier = Modifier) {
    val genres = remember(movie) {
        movie.genres.joinToString()
    }
    val originCountry = remember(movie) {
        movie.originCountry.joinToString()
    }
    val productionCompanies = remember(movie) {
        movie.productionCompanies.joinToString()
    }
    val productionCountries = remember(movie) {
        movie.productionCountries.joinToString()
    }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = movie.title, style = MaterialTheme.typography.titleLarge)
                Text(text = movie.tagline, style = MaterialTheme.typography.titleSmall)
                if (movie.adult) {
                    Icon(
                        painterResource(R.drawable.baseline_18_up_rating_24),
                        contentDescription = "adult movie"
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = "rating: ${movie.voteAverage} of ${movie.voteCount} votes"
                )
                Text(text = "${movie.voteAverage.formatLocalNumber()} (${movie.voteCount.formatLocalNumber()})")
            }
        }
        HorizontalDivider()
        Text(text = movie.overview)
        Text(text = "Genre: $genres")
        Text(text = "Runtime: ${movie.runtime} minutes")
        Text(text = "Status: ${movie.status}")
        Text(text = "Release date: ${movie.releaseDate}")
        Text(text = "Original language: ${movie.originalLanguage.uppercase()}")
        Text(text = "Budget: ${movie.budget.formatCurrency(Locale.US)}")
        Text(text = "Revenue: ${movie.revenue.formatCurrency(Locale.US)}")
        Text(text = "Origin country: $originCountry")
        Text(text = "Production companies: $productionCompanies")
        Text(text = "Production countries: $productionCountries")
        movie.belongsToCollection?.let {
            Text(text = "Collection: $it")
        }
    }
}