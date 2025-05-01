package id.ak.movieshighlight.domain.entity

data class TvSerial(
    val id: Int,
    val title: String,
    val originalLanguage: String,
    val genres: List<String>,
    val overview: String,
    val posterUrl: String,
    val originCountry: List<String>,
    val productionCompanies: List<String>,
    val productionCountries: List<String>,
    val firstAirDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val tagline: String?,
    val adult: Boolean,
    val homepage: String,
    val status: String,
    val networks: List<String>,
    val numberOfSeasons: Int,
    val numberOfEpisodes: Int,
    val seasons: List<TvSerialSeason>,
    val lastEpisodeToAir: TvSerialLastEpisode?,
    val episodeRunTime: List<Int>,
    val nextEpisodeToAir: TvSerialLastEpisode?,
    val inProduction: Boolean
)
