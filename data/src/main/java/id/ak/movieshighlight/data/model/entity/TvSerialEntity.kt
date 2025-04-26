package id.ak.movieshighlight.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.ak.movieshighlight.domain.entity.Watchlist

@Entity
data class TvSerialEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterUrl: String
)

fun TvSerialEntity.toDomain() = Watchlist(
    id = id,
    title = title,
    posterUrl = posterUrl,
    type = Watchlist.Type.TvSerial
)

fun Watchlist.toTvSerialEntity() = TvSerialEntity(
    id = id,
    title = title,
    posterUrl = posterUrl
)