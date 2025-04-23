package id.ak.movieshighlight.data.model

import id.ak.convention.data.BuildConfig
import id.ak.movieshighlight.domain.entity.Movie
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class MovieDetailsResponse(
    @SerialName("original_language")
    val originalLanguage: String? = null,

    @SerialName("imdb_id")
    val imdbId: String? = null,

    @SerialName("video")
    val video: Boolean? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    @SerialName("revenue")
    val revenue: Int? = null,

    @SerialName("genres")
    val genres: List<GenreModel>? = null,

    @SerialName("popularity")
    val popularity: Double? = null,

    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryModel>? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("vote_count")
    val voteCount: Int? = null,

    @SerialName("budget")
    val budget: Int? = null,

    @SerialName("overview")
    val overview: String? = null,

    @SerialName("original_title")
    val originalTitle: String? = null,

    @SerialName("runtime")
    val runtime: Int? = null,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("origin_country")
    val originCountry: List<String>? = null,

    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageModel>? = null,

    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyModel>? = null,

    @SerialName("release_date")
    val releaseDate: String? = null,

    @SerialName("vote_average")
    val voteAverage: Double? = null,

    @SerialName("belongs_to_collection")
    val belongsToCollection: BelongsToCollectionModel? = null,

    @SerialName("tagline")
    val tagline: String? = null,

    @SerialName("adult")
    val adult: Boolean? = null,

    @SerialName("homepage")
    val homepage: String? = null,

    @SerialName("status")
    val status: String? = null
) {
    fun toDomain() = Movie(
        id = id ?: 0,
        title = title ?: originalTitle.orEmpty(),
        originalLanguage = originalLanguage.orEmpty(),
        revenue = revenue ?: 0,
        genres = genres?.mapNotNull { it.name }.orEmpty(),
        productionCountries = productionCountries?.mapNotNull { it.name }.orEmpty(),
        budget = budget ?: 0,
        overview = overview.orEmpty(),
        runtime = runtime ?: 0,
        posterUrl = posterPath?.let {
            BuildConfig.BASE_IMAGE_URL + BuildConfig.POSTER_SIZE + it
        }.orEmpty(),
        originCountry = originCountry.orEmpty(),
        productionCompanies = productionCompanies?.mapNotNull { it.name }.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        voteAverage = voteAverage ?: .0,
        voteCount = voteCount ?: 0,
        belongsToCollection = belongsToCollection?.name,
        tagline = tagline.orEmpty(),
        adult = adult == true,
        homepage = homepage.orEmpty(),
        status = status.orEmpty()
    )
}
