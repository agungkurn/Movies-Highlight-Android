package id.ak.movieshighlight.domain.result

sealed interface UseCaseResult<out R : Any> {
    data class Success<out T : Any>(val data: T) : UseCaseResult<T>
    data class Failed(val message: String?) : UseCaseResult<Nothing>
}