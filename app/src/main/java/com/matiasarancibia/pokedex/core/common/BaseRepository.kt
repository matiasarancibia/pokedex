package com.matiasarancibia.pokedex.core.common

import android.util.Log
import kotlinx.coroutines.coroutineScope

/*
    This BaseRepository interface will be used to handle the result of the API calls and
    return them in a own clean and easy-to-use Result object with the specific state depending on
    the operation result. This class will be extended by all the repositories in the application
 */
interface BaseRepository {

    suspend fun <T> request(
        call: suspend () -> T
    ): Result<T> = coroutineScope {
        try {
            Result.Companion.success(call())
        } catch (exception: Exception) {
            exception.localizedMessage?.let {
                Log.e("EXCEPTION", it)
            }

            Result.Companion.error(exception)
        }
    }
}