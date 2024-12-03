package com.lwg.data.util

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

internal sealed interface ApiResult<out T> {
    data class Success<T>(val response: T) : ApiResult<T>

    sealed interface Failure : ApiResult<Nothing> {
        data class HttpError(val code: Int, val message: String, val body: String) : Failure
        data class CustomError(val code: Int, val message: String) : Failure
        data object NetworkError : Failure
        data class UnknownApiError(val throwable: Throwable) : Failure
    }
}

@OptIn(ExperimentalContracts::class)
internal inline fun <T> ApiResult<T>.onSuccess(
    crossinline action: ApiResult.Success<T>.() -> Unit
): ApiResult<T> {
    contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }
    if (this is ApiResult.Success) {
        action(this)
    }
    return this
}

@OptIn(ExperimentalContracts::class)
internal suspend inline fun <T> ApiResult<T>.suspendOnSuccess(
    crossinline action: suspend ApiResult.Success<T>.() -> Unit
): ApiResult<T> {
    contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }
    if (this is ApiResult.Success) {
        action(this)
    }
    return this
}

@OptIn(ExperimentalContracts::class)
internal inline fun <T> ApiResult<T>.onFailure(
    crossinline action: ApiResult.Failure.() -> Unit
): ApiResult<T> {
    contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }
    if (this is ApiResult.Failure) {
        action(this)
    }
    return this
}

@OptIn(ExperimentalContracts::class)
internal suspend inline fun <T> ApiResult<T>.suspendOnFailure(
    crossinline action: suspend ApiResult.Failure.() -> Unit
): ApiResult<T> {
    contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }
    if (this is ApiResult.Failure) {
        action(this)
    }
    return this
}

@OptIn(ExperimentalContracts::class)
internal inline fun <T> ApiResult<T>.suspendOnFailureWithErrorHandling(
    crossinline onError: (String) -> Unit = {}
): ApiResult<T> {
    contract { callsInPlace(onError, InvocationKind.AT_MOST_ONCE) }
    if (this is ApiResult.Failure) {
        when (this) {
            is ApiResult.Failure.HttpError -> {
                onError(message)
            }

            ApiResult.Failure.NetworkError -> {
                onError("네트워크 오류가 발생하였습니다. 네트워크를 확인하여 주세요.")
            }

            is ApiResult.Failure.UnknownApiError -> {

            }

            is ApiResult.Failure.CustomError -> {
                onError(message)
            }
        }
    }
    return this
}
