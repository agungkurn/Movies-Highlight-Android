package id.ak.movieshighlight.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ak.movieshighlight.domain.entity.Movie
import id.ak.movieshighlight.domain.entity.TvSerial
import id.ak.movieshighlight.domain.result.UseCaseResult
import id.ak.movieshighlight.domain.usecase.GetMovieDetails
import id.ak.movieshighlight.domain.usecase.GetTvSerialDetails
import id.ak.movieshighlight.ui.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetails,
    private val getTvSerialDetailsUseCase: GetTvSerialDetails
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.NotLoading)
    val uiState get() = _uiState.asStateFlow()

    private val _movieDetails = MutableStateFlow<Movie?>(null)
    val movieDetails get() = _movieDetails.asStateFlow()

    private val _tvSerialDetails = MutableStateFlow<TvSerial?>(null)
    val tvSerialDetails get() = _tvSerialDetails.asStateFlow()

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
}