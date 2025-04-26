package id.ak.movieshighlight.data.model.response

import id.ak.convention.data.BuildConfig
import id.ak.movieshighlight.domain.entity.TvSerialSeason
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvSeasonModel(

    @SerialName("air_date")
    val airDate: String? = null,

    @SerialName("overview")
    val overview: String? = null,

    @SerialName("episode_count")
    val episodeCount: Int? = null,

    @SerialName("vote_average")
    val voteAverage: Double? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("season_number")
    val seasonNumber: Int? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("poster_path")
    val posterPath: String? = null
) {
    fun toDomain() = TvSerialSeason(
        id = id ?: 0,
        seasonNumber = seasonNumber ?: 0,
        name = name.orEmpty(),
        overview = overview.orEmpty(),
        airDate = airDate.orEmpty(),
        episodeCount = episodeCount ?: 0,
        voteAverage = voteAverage ?: .0,
        posterUrl = posterPath?.let { BuildConfig.BASE_IMAGE_URL + BuildConfig.POSTER_SIZE + it }
    )
}
