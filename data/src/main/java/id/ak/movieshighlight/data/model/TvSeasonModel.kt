package id.ak.movieshighlight.data.model

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
    val voteAverage: Int? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("season_number")
    val seasonNumber: Int? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("poster_path")
    val posterPath: String? = null
)
