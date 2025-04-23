package id.ak.movieshighlight.data.model

import id.ak.convention.data.BuildConfig
import id.ak.movieshighlight.domain.entity.TvSerial
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiscoverTvModel(
    @SerialName("first_air_date")
    val firstAirDate: String? = null,

    @SerialName("overview")
    val overview: String? = null,

    @SerialName("original_language")
    val originalLanguage: String? = null,

    @SerialName("genre_ids")
    val genreIds: List<Int?>? = null,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("origin_country")
    val originCountry: List<String>? = null,

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    @SerialName("original_name")
    val originalName: String? = null,

    @SerialName("popularity")
    val popularity: Double? = null,

    @SerialName("vote_average")
    val voteAverage: Double? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("adult")
    val adult: Boolean? = null,

    @SerialName("vote_count")
    val voteCount: Int? = null
) {
    fun toDomain() = TvSerial(
        id = id ?: 0,
        overview = overview.orEmpty(),
        originalLanguage = originalLanguage.orEmpty(),
        title = (name ?: originalName).orEmpty(),
        posterPath = posterPath?.let {
            BuildConfig.BASE_IMAGE_URL + BuildConfig.POSTER_SIZE + it
        }.orEmpty(),
        firstAirDate = firstAirDate.orEmpty(),
        adult = adult == true,
        voteAverage = voteAverage ?: .0,
        voteCount = voteCount ?: 0,
        originCountry = originCountry.orEmpty()
    )
}
