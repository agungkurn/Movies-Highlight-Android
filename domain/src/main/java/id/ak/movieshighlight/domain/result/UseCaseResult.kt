package id.ak.movieshighlight.domain.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

sealed class UseCaseResult<out R : Any> {
    data class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    data class Failed(val message: String?) : UseCaseResult<Nothing>()
}

fun <T : Any> Flow<T>.asUseCaseResult(): Flow<UseCaseResult<T>> =
    map { UseCaseResult.Success(it) as UseCaseResult<T> }
        .catch { emit(UseCaseResult.Failed(it.message) as UseCaseResult<T>) }

fun <T : Any> Flow<UseCaseResult<T>>.getData(
    onSuccess: () -> Unit = {},
    onError: (String?) -> Unit = {}
) = map {
    when (it) {
        is UseCaseResult.Success<T> -> {
            onSuccess()
            it.data
        }

        is UseCaseResult.Failed -> {
            onError(it.message)
            null
        }
    }
}