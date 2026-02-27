package com.matiasarancibia.pokedex.core.common

import android.content.Context
import com.matiasarancibia.pokedex.domain.model.APIErrorViewData
import com.matiasarancibia.pokedex.ui.util.extensions.empty

// This sealed interface will be used to handle all the different UI states in the View Models
sealed interface UiState<T> {

    class Default<T> : UiState<T>

    // Normal loading state
    class Loading<T> : UiState<T>

    // Used to show the data in the UI if the request was successful
    data class Success<T>(
        var data: T,
        var refresh: Boolean = false,
        var isPaging: Boolean = false
    ) : UiState<T>

    // Used to show an error in the UI
    data class Error<T>(
        val errorMessage: String? = "Error",
        val errorTitle: String? = null,
        val errorCode: Int? = null,
        val error: Throwable? = null,
        val errorData: APIErrorViewData? = null
    ) : UiState<T>

    /*
        Used to perform an action when there is no data to show in the UI.
        In example: a listing with no results to be shown will use this state
     */
    data class EmptyResult<T>(
        val title: String? = null,
        val message: String? = null,
        val messageResId: Int? = null
    ) : UiState<T> {
        fun getMessage(context: Context): String {
            return when {
                messageResId != null -> context.getString(messageResId)
                !message.isNullOrEmpty() -> message
                else -> {
                    String.empty()
                }
            }
        }
    }

    // These functions will be used as a default value to initialize the necessary StateFlows
    companion object {
        fun <T> default(): UiState<T> = Default()

        fun <T> loading(): UiState<T> = Loading()

        fun <T> success(data: T): UiState<T> = Success(data)

        @JvmOverloads
        fun <T> error(
            errorData: APIErrorViewData?,
            errorMessage: String? = null,
            errorTitle: String? = null,
            errorCode: Int? = null,
            error: Throwable? = null
        ): UiState<T> = Error(errorMessage, errorTitle, errorCode, error, errorData)

        fun <T> emptyResult(
            title: String? = null,
            message: String? = null,
            messageResId: Int? = null
        ): UiState<T> = EmptyResult(title, message, messageResId)
    }
}