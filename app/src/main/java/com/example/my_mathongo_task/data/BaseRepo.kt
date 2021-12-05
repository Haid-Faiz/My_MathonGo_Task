package com.example.my_mathongo_task.data

import com.example.my_mathongo_task.utils.ErrorType
import com.example.my_mathongo_task.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepo {

    suspend fun <T> safeApiCall(api: suspend () -> Response<T>): Resource<T> {

        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = api()
                Resource.Success(data = response.body()!!)
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> Resource.Error(
                        "Please check your network connection",
                        errorType = ErrorType.NETWORK
                    )

                    is HttpException ->
                        // val code = e.code() HTTP Exception code
                        Resource.Error(
                            errorMessage = throwable.message ?: "Something went wrong",
                            errorType = ErrorType.HTTP
                        )

                    else -> Resource.Error(
                        errorMessage = "Something went wrong",
                        errorType = ErrorType.UNKNOWN
                    )
                }
            }
        }
    }
}
