package ru.glebik.core.utils

sealed interface ResultWrapper<out T> {
    data class Success<out T>(val data: T) : ResultWrapper<T>
    data class Failed(
        val exception: Throwable? = null,
        val errorMessage: String? = null,
        val code: Int? = null,
    ) : ResultWrapper<Nothing>
}
