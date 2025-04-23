package id.ak.movieshighlight.domain.entity

data class TvSerialLastEpisode(
    val id: Int,
    val name: String,
    val overview: String,
    val showId: Int,
    val seasonNumber: Int,
    val runtime: Int,
    val stillUrl: String?,
    val airDate: String,
    val episodeNumber: Int,
    val voteAverage: Double,
    val voteCount: Int
)
