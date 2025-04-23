package id.ak.movieshighlight.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeModel(
    @SerialName("episode_type")
    val episodeType: String? = null,

    @SerialName("production_code")
    val productionCode: String? = null,

    @SerialName("overview")
    val overview: String? = null,

    @SerialName("show_id")
    val showId: Int? = null,

    @SerialName("season_number")
    val seasonNumber: Int? = null,

    @SerialName("runtime")
    val runtime: Int? = null,

    @SerialName("still_path")
    val stillPath: String? = null,

    @SerialName("air_date")
    val airDate: String? = null,

    @SerialName("episode_number")
    val episodeNumber: Int? = null,

    @SerialName("vote_average")
    val voteAverage: Int? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("vote_count")
    val voteCount: Int? = null
)
