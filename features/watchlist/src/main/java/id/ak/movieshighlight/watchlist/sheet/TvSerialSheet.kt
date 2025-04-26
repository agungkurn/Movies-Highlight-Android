package id.ak.movieshighlight.watchlist.sheet

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import id.ak.convention.features.details.R
import id.ak.movieshighlight.core.ext.formatLocalNumber
import id.ak.movieshighlight.domain.entity.TvSerial

@Composable
fun TvSerialSheet(tvSerial: TvSerial, modifier: Modifier = Modifier) {
    var expandSeasons by remember { mutableStateOf(false) }
    val expandIconDegree by animateFloatAsState(targetValue = if (expandSeasons) 180f else 0f)

    val genres = remember(tvSerial) {
        tvSerial.genres.joinToString()
    }
    val originCountry = remember(tvSerial) {
        tvSerial.originCountry.joinToString()
    }
    val productionCompanies = remember(tvSerial) {
        tvSerial.productionCompanies.joinToString()
    }
    val productionCountries = remember(tvSerial) {
        tvSerial.productionCountries.joinToString()
    }
    val runtime = remember(tvSerial) {
        if (tvSerial.episodeRunTime.size > 1) {
            val min = tvSerial.episodeRunTime.min()
            val max = tvSerial.episodeRunTime.max()
            "$min - $max"
        } else {
            tvSerial.episodeRunTime.firstOrNull()?.let {
                "$it"
            }
        }
    }
    val networks = remember(tvSerial) {
        tvSerial.networks.joinToString()
    }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = tvSerial.title, style = MaterialTheme.typography.titleLarge)
                tvSerial.tagline?.let {
                    Text(text = it, style = MaterialTheme.typography.titleSmall)
                }
                if (tvSerial.adult) {
                    Icon(
                        painterResource(R.drawable.baseline_18_up_rating_24),
                        contentDescription = "adult show"
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = "rating: ${tvSerial.voteAverage} of ${tvSerial.voteCount} votes"
                )
                Text(text = "${tvSerial.voteAverage.formatLocalNumber()} (${tvSerial.voteCount.formatLocalNumber()})")
            }
        }
        HorizontalDivider()
        if (tvSerial.inProduction) {
            Text(text = "Still in production")
        }
        Text(text = tvSerial.overview)
        Text(text = "Genre: $genres")
        Text(text = "Runtime: $runtime minutes")
        Text(text = "Status: ${tvSerial.status}")
        Text(text = "Original language: ${tvSerial.originalLanguage.uppercase()}")
        Text(text = "Origin country: $originCountry")
        Text(text = "Production companies: $productionCompanies")
        Text(text = "Production countries: $productionCountries")
        Text(text = "Networks: $networks")
        AnimatedContent(expandSeasons) { expanded ->
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = { expandSeasons = !expanded }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "${tvSerial.numberOfSeasons} Seasons, ${tvSerial.numberOfEpisodes.formatLocalNumber()} Episodes",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Icon(
                            modifier = Modifier.rotate(expandIconDegree),
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "show seasons"
                        )
                    }
                    if (expanded) {
                        tvSerial.seasons.forEach {
                            Column {
                                Text(
                                    text = "Season ${it.seasonNumber}: ${it.name}",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                it.overview?.let { s -> Text(text = s) }
                                Text(text = "Air date: ${it.airDate}")
                                Text(text = "Episodes: ${it.episodeCount}")
                                Text(text = "Vote avg.: ${it.voteAverage}")
                            }
                        }
                    }
                }
            }
        }
    }
}