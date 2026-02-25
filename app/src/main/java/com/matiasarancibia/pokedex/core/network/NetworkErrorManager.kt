package com.matiasarancibia.pokedex.core.network

import android.content.Context
import com.google.gson.Gson
import com.matiasarancibia.pokedex.R
import com.matiasarancibia.pokedex.data.model.APIErrorData
import com.matiasarancibia.pokedex.domain.mappers.APIErrorVDMapper
import com.matiasarancibia.pokedex.domain.model.APIErrorViewData
import com.matiasarancibia.pokedex.ui.util.extensions.orElse
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

/*
    This NetworkErrorManager class will help us to parse and manage the different type of
    errors that we could get from the server
 */
class NetworkErrorManager @Inject constructor(
    private val context: Context,
    private val apiErrorVDMapper: APIErrorVDMapper
) {
    /**
     * Gets the API error body response (in case is http exception)
     * and transform it to APIErrorViewData class
     *
     * @param exception         The exception to work on
     * @param defaultErrorMsg   Optional default error message to override the actual one
     */
    fun handleAPIErrorResponse(
        exception: Throwable?,
        defaultErrorMsg: String? = null
    ): APIErrorViewData {
        val defaultErrorTitle = context.getString(R.string.default_error_title)
        val defaultErrorMessage = if (defaultErrorMsg.isNullOrEmpty()) {
            context.getString(R.string.default_network_error_message)
        } else {
            defaultErrorMsg
        }

        /*
            Here we manage the different types of exceptions by performing different actions for
            most of them, and in this way we can obtain different results or behaviors depending on
            the type of exception we've received from the server
         */
        return when (exception) {
            is HttpException -> {
                // APIErrorViewData object is created with default error title and message
                var apiErrorViewData: APIErrorViewData? = APIErrorViewData(
                    realHttpStatusCode = exception.code(),
                    errorCode = exception.code().toString(),
                    errorTitle = defaultErrorTitle,
                    errorMessage = defaultErrorMessage
                )

                try {
                    exception.response()?.let { response ->
                        val parsedError = Gson().fromJson(
                            response.errorBody()?.string(),
                            APIErrorData::class.java
                        )
                        apiErrorViewData = apiErrorVDMapper
                            .executeMapping(parsedError)
                            .orElse(APIErrorViewData())

                        apiErrorViewData.let {
                            it.realHttpStatusCode = exception.code()

                            if (it.errorTitle.isNullOrBlank()) {
                                it.errorTitle = defaultErrorTitle
                            }

                            if (it.errorMessage.isNullOrBlank()) {
                                it.errorMessage = defaultErrorMessage
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                return apiErrorViewData ?: APIErrorViewData(
                    errorTitle = defaultErrorTitle,
                    errorMessage = defaultErrorMessage
                )
            }

            is UnknownHostException -> {
                /*
                    Here we don't add any specific behavior for this exception, but it could be
                    customized if needed
                 */
                APIErrorViewData(
                    errorTitle = defaultErrorTitle,
                    errorMessage = defaultErrorMessage
                )
            }

            is ConnectException -> {
                /*
                    Here we don't add any specific behavior for this exception, but it could be
                    customized if needed
                 */
                APIErrorViewData(
                    errorTitle = defaultErrorTitle,
                    errorMessage = defaultErrorMessage
                )
            }

            is SocketException -> {
                /*
                    Here we don't add any specific behavior for this exception, but it could be
                    customized if needed
                 */
                APIErrorViewData(
                    errorTitle = defaultErrorTitle,
                    errorMessage = defaultErrorMessage
                )
            }

            is SocketTimeoutException -> {
                /*
                    Here we don't add any specific behavior for this exception, but it could be
                    customized if needed
                 */
                APIErrorViewData(
                    errorTitle = defaultErrorTitle,
                    errorMessage = defaultErrorMessage
                )
            }

            else -> {
                // This is the default behavior for other exceptions not handled before
                exception?.printStackTrace()

                APIErrorViewData(
                    errorTitle = defaultErrorTitle,
                    errorMessage = defaultErrorMessage
                )
            }
        }
    }
}