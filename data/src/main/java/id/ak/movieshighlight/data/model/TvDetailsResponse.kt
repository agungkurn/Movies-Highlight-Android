package id.ak.movieshighlight.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class TvDetailsResponse(
    @SerialName("original_language")
    val originalLanguage: String? = null,

    @SerialName("number_of_episodes")
    val numberOfEpisodes: Int? = null,

    @SerialName("networks")
    val networks: List<TvNetworkModel>? = null,

    @SerialName("type")
    val type: String? = null,

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    @SerialName("genres")
    val genres: List<GenreModel>? = null,

    @SerialName("popularity")
    val popularity: Double? = null,

    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryModel>? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("number_of_seasons")
    val numberOfSeasons: Int? = null,

    @SerialName("vote_count")
    val voteCount: Int? = null,

    @SerialName("first_air_date")
    val firstAirDate: String? = null,

    @SerialName("overview")
    val overview: String? = null,

    @SerialName("seasons")
    val seasons: List<TvSeasonModel>? = null,

    @SerialName("languages")
    val languages: List<String>? = null,

    @SerialName("created_by")
    val createdBy: List<CreatorModel>? = null,

    @SerialName("last_episode_to_air")
    val lastEpisodeToAir: EpisodeModel? = null,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("origin_country")
    val originCountry: List<String>? = null,

    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageModel>? = null,

    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyModel>? = null,

    @SerialName("original_name")
    val originalName: String? = null,

    @SerialName("vote_average")
    val voteAverage: Double? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("tagline")
    val tagline: String? = null,

    @SerialName("episode_run_time")
    val episodeRunTime: List<Int>? = null,

    @SerialName("adult")
    val adult: Boolean? = null,

    @SerialName("next_episode_to_air")
    val nextEpisodeToAir: String? = null,

    @SerialName("in_production")
    val inProduction: Boolean? = null,

    @SerialName("last_air_date")
    val lastAirDate: String? = null,

    @SerialName("homepage")
    val homepage: String? = null,

    @SerialName("status")
    val status: String? = null
)
