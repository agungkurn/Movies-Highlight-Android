package id.ak.movieshighlight.ui.state

sealed interface UiState {
    data object Loading : UiState
    data object NotLoading : UiState
    data class Error(val message: String?) : UiState
}