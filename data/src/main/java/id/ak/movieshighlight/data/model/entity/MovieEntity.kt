package id.ak.movieshighlight.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.ak.movieshighlight.domain.entity.Watchlist

@Entity
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterUrl: String
)

fun MovieEntity.toDomain() = Watchlist(
    id = id,
    title = title,
    posterUrl = posterUrl,
    type = Watchlist.Type.Movie
)

fun Watchlist.toMovieEntity() = MovieEntity(
    id = id,
    title = title,
    posterUrl = posterUrl,
)