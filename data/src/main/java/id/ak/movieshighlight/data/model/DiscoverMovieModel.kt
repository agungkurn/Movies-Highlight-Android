package id.ak.movieshighlight.data.model

import id.ak.convention.data.BuildConfig
import id.ak.movieshighlight.domain.entity.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiscoverMovieModel(
    @SerialName("overview")
    val overview: String? = null,

    @SerialName("original_language")
    val originalLanguage: String? = null,

    @SerialName("original_title")
    val originalTitle: String? = null,

    @SerialName("video")
    val video: Boolean? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("genre_ids")
    val genreIds: List<Int?>? = null,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    @SerialName("release_date")
    val releaseDate: String? = null,

    @SerialName("popularity")
    val popularity: Double? = null,

    @SerialName("vote_average")
    val voteAverage: Double? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("adult")
    val adult: Boolean? = null,

    @SerialName("vote_count")
    val voteCount: Int? = null
) {
    fun toDomain() = Movie(
        id = id ?: 0,
        overview = overview.orEmpty(),
        originalLanguage = originalLanguage.orEmpty(),
        title = (title ?: originalTitle).orEmpty(),
        posterPath = posterPath?.let {
            BuildConfig.BASE_IMAGE_URL + BuildConfig.POSTER_SIZE + it
        }.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        adult = adult == true,
        voteAverage = voteAverage ?: .0,
        voteCount = voteCount ?: 0
    )
}
