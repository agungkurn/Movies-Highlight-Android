package id.ak.movieshighlight.domain.entity

data class TvSerialSeason(
    val id: Int,
    val seasonNumber: Int,
    val name: String,
    val overview: String?,
    val airDate: String,
    val episodeCount: Int,
    val voteAverage: Double,
    val posterUrl: String?
)
