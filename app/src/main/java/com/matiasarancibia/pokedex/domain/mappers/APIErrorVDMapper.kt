package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.R
import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.APIErrorData
import com.matiasarancibia.pokedex.domain.model.APIErrorViewData
import com.matiasarancibia.pokedex.ui.util.AppResourcesManager
import com.matiasarancibia.pokedex.ui.util.extensions.orElse
import javax.inject.Inject

class APIErrorVDMapper @Inject constructor(
    private val appResourcesManager: AppResourcesManager
) : Mapper<APIErrorData?, APIErrorViewData?> {

    override fun executeMapping(data: APIErrorData?): APIErrorViewData? {
        return data?.let {
            APIErrorViewData(
                errorTitle = appResourcesManager.getString(R.string.default_error_title),
                errorMessage = data.localisedMessage.orElse(data.errorMessage),
                errorId = data.errorId,
                errorCode = data.errorCode,
                httpStatusCode = data.httpStatusCode
            )
        }
    }
}