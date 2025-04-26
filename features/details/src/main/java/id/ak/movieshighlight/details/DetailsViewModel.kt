package id.ak.movieshighlight.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ak.movieshighlight.domain.entity.Movie
import id.ak.movieshighlight.domain.entity.TvSerial
import id.ak.movieshighlight.domain.entity.Watchlist
import id.ak.movieshighlight.domain.result.UseCaseResult
import id.ak.movieshighlight.domain.usecase.details.GetMovieDetails
import id.ak.movieshighlight.domain.usecase.details.GetTvSerialDetails
import id.ak.movieshighlight.domain.usecase.details.IsInWatchlist
import id.ak.movieshighlight.domain.usecase.watchlist.AddToWatchlist
import id.ak.movieshighlight.domain.usecase.watchlist.RemoveFromWatchlist
import id.ak.movieshighlight.ui.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetails,
    private val getTvSerialDetailsUseCase: GetTvSerialDetails,
    private val isInWatchlistUseCase: IsInWatchlist,
    private val addToWatchlistUseCase: AddToWatchlist,
    private val removeFromWatchlistUseCase: RemoveFromWatchlist,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.NotLoading)
    val uiState get() = _uiState.asStateFlow()

    private val _movieDetails = MutableStateFlow<Movie?>(null)
    val movieDetails get() = _movieDetails.asStateFlow()

    private val _tvSerialDetails = MutableStateFlow<TvSerial?>(null)
    val tvSerialDetails get() = _tvSerialDetails.asStateFlow()

    private val _loadingWatchlist = MutableStateFlow(false)
    val loadingWatchlist get() = _loadingWatchlist.asStateFlow()

    val isInWatchlist = combine(movieDetails, tvSerialDetails) { movie, tvSerial ->
        movie?.id?.let {
            isInWatchlistUseCase.movie(it)
        } ?: tvSerial?.id?.let {
            isInWatchlistUseCase.tvSerial(it)
        } ?: flowOf(null)
    }.flattenConcat()

    fun fetchMovieDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading

            when (val result = getMovieDetailsUseCase(id)) {
                is UseCaseResult.Failed -> _uiState.value = UiState.Error(result.message)
                is UseCaseResult.Success<Movie> -> {
                    _uiState.value = UiState.NotLoading
                    _movieDetails.value = result.data
                }
            }
        }
    }

    fun fetchTvSerialDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading

            when (val result = getTvSerialDetailsUseCase(id)) {
                is UseCaseResult.Failed -> _uiState.value = UiState.Error(result.message)
                is UseCaseResult.Success<TvSerial> -> {
                    _uiState.value = UiState.NotLoading
                    _tvSerialDetails.value = result.data
                }
            }
        }
    }

    fun addToWatchlist() {
        viewModelScope.launch(Dispatchers.IO) {
            _loadingWatchlist.value = true

            movieDetails.value?.let {
                addToWatchlistUseCase.asMovie(
                    id = it.id,
                    title = it.title,
                    posterUrl = it.posterUrl
                )

                return@launch
            }

            tvSerialDetails.value?.let {
                addToWatchlistUseCase.asTvSerial(
                    id = it.id,
                    title = it.title,
                    posterUrl = it.posterUrl
                )

                return@launch
            }
        }.invokeOnCompletion {
            _loadingWatchlist.value = false
        }
    }

    fun removeFromWatchlist() {
        viewModelScope.launch(Dispatchers.IO) {
            _loadingWatchlist.value = true

            val id = movieDetails.value?.id ?: tvSerialDetails.value?.id
            val type = movieDetails.value?.let { Watchlist.Type.Movie }
                ?: tvSerialDetails.value?.let { Watchlist.Type.TvSerial }

            if (id != null && type != null) {
                removeFromWatchlistUseCase(id = id, type = type)
            }
        }.invokeOnCompletion {
            _loadingWatchlist.value = false
        }
    }
}