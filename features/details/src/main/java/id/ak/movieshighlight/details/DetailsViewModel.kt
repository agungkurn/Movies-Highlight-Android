package id.ak.movieshighlight.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ak.movieshighlight.domain.entity.Movie
import id.ak.movieshighlight.domain.entity.TvSerial
import id.ak.movieshighlight.domain.entity.Watchlist
import id.ak.movieshighlight.domain.result.getData
import id.ak.movieshighlight.domain.usecase.details.GetMovieDetails
import id.ak.movieshighlight.domain.usecase.details.GetTvSerialDetails
import id.ak.movieshighlight.domain.usecase.details.IsInWatchlist
import id.ak.movieshighlight.domain.usecase.watchlist.AddToWatchlist
import id.ak.movieshighlight.domain.usecase.watchlist.RemoveFromWatchlist
import id.ak.movieshighlight.ui.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
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

    private val movieId = MutableStateFlow<Int?>(null)
    private val tvSeriesId = MutableStateFlow<Int?>(null)

    val movieDetails: StateFlow<Movie?> = movieId.filterNotNull()
        .onStart { _uiState.value = UiState.Loading }
        .flatMapLatest { getMovieDetailsUseCase(it) }
        .getData(
            onSuccess = { _uiState.value = UiState.NotLoading },
            onError = { _uiState.value = UiState.Error(it) }
        )
        .stateIn(viewModelScope, started = SharingStarted.WhileSubscribed(), initialValue = null)

    val tvSerialDetails: StateFlow<TvSerial?> = tvSeriesId.filterNotNull()
        .onStart { _uiState.value = UiState.Loading }
        .flatMapLatest { getTvSerialDetailsUseCase(it) }
        .getData(
            onSuccess = { _uiState.value = UiState.NotLoading },
            onError = { _uiState.value = UiState.Error(it) }
        )
        .stateIn(viewModelScope, started = SharingStarted.WhileSubscribed(), initialValue = null)

    private val _loadingWatchlist = MutableStateFlow(false)
    val loadingWatchlist get() = _loadingWatchlist.asStateFlow()

    val isInWatchlist = combine(movieId, tvSeriesId) { movieId, tvSeriesId ->
        movieId?.let {
            isInWatchlistUseCase.movie(it)
        } ?: tvSeriesId?.let {
            isInWatchlistUseCase.tvSerial(it)
        } ?: flowOf(null)
    }.flattenConcat()

    fun setMovieId(id: Int) {
        movieId.value = null        // triggers refresh
        movieId.value = id
    }

    fun setTvSerialId(id: Int) {
        tvSeriesId.value = null     // triggers refresh
        tvSeriesId.value = id
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