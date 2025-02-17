package com.lwg.data.util

import com.lwg.util.Logger
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

internal class ApiResultCall<R>(
  proxy: Call<R>,
  private val coroutineScope: CoroutineScope,
) : CallDelegate<R, ApiResult<R>>(proxy) {


  override fun enqueueImpl(callback: Callback<ApiResult<R>>) = proxy.enqueue(
    object : Callback<R> {
      override fun onResponse(call: Call<R>, response: Response<R>) {
        callback.onResponse(this@ApiResultCall, Response.success(response.toApiResult()))
      }

      private fun Response<R>.toApiResult(): ApiResult<R> {
        if (!isSuccessful) {
          val errorBody = errorBody()!!.string()
          return ApiResult.Failure.HttpError(
              status_code = code(),
              status_message = message(),
              success = isSuccessful
          )
        }
        body()?.let { body ->
            return ApiResult.Success(body)
        }

        return ApiResult.Failure.UnknownApiError(
            IllegalStateException(
                "Body가 존재하지 않지만, Unit 이외의 타입으로 정의했습니다. ApiResult<Unit>로 정의하세요."
            )
        )
      }

      override fun onFailure(call: Call<R?>, throwable: Throwable) {
        val error = if (throwable is IOException) {
            ApiResult.Failure.NetworkError
        } else {
            ApiResult.Failure.UnknownApiError(throwable)
        }
        callback.onResponse(this@ApiResultCall, Response.success(error))
      }
    }
  )

  override fun clone(): Call<ApiResult<R>> = ApiResultCall(proxy.clone(), coroutineScope)

  override fun execute(): Response<ApiResult<R>> {
    throw UnsupportedOperationException("ApiResultCall doesn't support execute")
  }
}

