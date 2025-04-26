package id.ak.movieshighlight.watchlist.list

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import id.ak.movieshighlight.domain.entity.Watchlist

@Composable
fun EmptyState(type: Watchlist.Type?, modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        type?.let {
            Text(text = "No ${it.displayName} watchlist")
        } ?: Text(text = "No watchlist yet")
    }
}