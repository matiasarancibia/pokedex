package com.matiasarancibia.pokedex.ui.components.errorComponent

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matiasarancibia.pokedex.R
import com.matiasarancibia.pokedex.domain.model.APIErrorViewData
import com.matiasarancibia.pokedex.ui.theme.LargeRoundedCornerShape
import com.matiasarancibia.pokedex.ui.util.extensions.ifCondition
import com.matiasarancibia.pokedex.ui.util.extensions.letNotEmpty
import com.matiasarancibia.pokedex.ui.util.extensions.orFalse

@Composable
fun ErrorViewComponent(
    apiErrorVD: APIErrorViewData?,
    isFullScreen: Boolean = true,
    errorIcon: (@Composable () -> Unit)? = null,
    onCloseClick: (APIErrorViewData) -> Unit = {},
    onSettingsClick: (APIErrorViewData) -> Unit = {},
    onTryAgainClick: (APIErrorViewData) -> Unit = {},
    onCustomActionClick: (APIErrorViewData) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .ifCondition(isFullScreen) {
                Modifier.fillMaxSize()
            }
            .background(color = MaterialTheme.colorScheme.background)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        errorIcon?.invoke()

        apiErrorVD?.errorTitle?.letNotEmpty { errorTitle ->
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = errorTitle,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            )
        }

        apiErrorVD?.errorMessage?.letNotEmpty { errorMessage ->
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = errorMessage,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            )
        }

        ButtonsContainer(
            apiErrorViewData = apiErrorVD,
            onCloseClick = onCloseClick,
            onSettingsClick = onSettingsClick,
            onCustomActionClick = onCustomActionClick,
            onTryAgainClick = onTryAgainClick
        )
    }
}

@Composable
private fun ButtonsContainer(
    apiErrorViewData: APIErrorViewData?,
    onCloseClick: (APIErrorViewData) -> Unit,
    onCustomActionClick: (APIErrorViewData) -> Unit,
    onSettingsClick: (APIErrorViewData) -> Unit,
    onTryAgainClick: (APIErrorViewData) -> Unit
) {
    val isCustomActionButtonEnabled = apiErrorViewData?.showCustomAction.orFalse().and(
        !apiErrorViewData?.customActionButtonText.isNullOrBlank()
    )

    Spacer(modifier = Modifier.height(10.dp))

    ErrorViewButton.entries.forEach { errorButton ->
        val isButtonEnabled = when (errorButton) {
            ErrorViewButton.SETTINGS -> apiErrorViewData?.showSetting
            ErrorViewButton.TRY_AGAIN -> apiErrorViewData?.showTryAgain
            ErrorViewButton.CLOSE -> apiErrorViewData?.showClose
            ErrorViewButton.CUSTOM_ACTION -> isCustomActionButtonEnabled
        }

        if (isButtonEnabled == true && apiErrorViewData != null) {
            ActionButtonComponent(
                buttonType = errorButton,
                apiErrorViewData = apiErrorViewData,
                onCloseClick = onCloseClick,
                onCustomActionClick = onCustomActionClick,
                onSettingsClick = onSettingsClick,
                onTryAgainClick = onTryAgainClick
            )
        }
    }
}

@Composable
private fun ActionButtonComponent(
    buttonType: ErrorViewButton,
    apiErrorViewData: APIErrorViewData,
    onCloseClick: (APIErrorViewData) -> Unit,
    onCustomActionClick: (APIErrorViewData) -> Unit,
    onSettingsClick: (APIErrorViewData) -> Unit,
    onTryAgainClick: (APIErrorViewData) -> Unit
) {
    val title = when (buttonType) {
        ErrorViewButton.SETTINGS -> stringResource(id = R.string.action_button_settings)
        ErrorViewButton.TRY_AGAIN -> stringResource(id = R.string.action_try_again)
        ErrorViewButton.CLOSE -> stringResource(id = R.string.action_close)
        ErrorViewButton.CUSTOM_ACTION -> apiErrorViewData.customActionButtonText
    }

    title?.letNotEmpty {
        OutlinedButton(
            shape = LargeRoundedCornerShape,
            border = BorderStroke(
                color = MaterialTheme.colorScheme.primary,
                width = 2.dp
            ),
            contentPadding = PaddingValues(
                horizontal = 32.dp,
                vertical = 12.dp
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            onClick = {
                when (buttonType) {
                    ErrorViewButton.SETTINGS -> {
                        onSettingsClick(apiErrorViewData)
                    }

                    ErrorViewButton.TRY_AGAIN -> {
                        onTryAgainClick(apiErrorViewData)
                    }

                    ErrorViewButton.CLOSE -> {
                        onCloseClick(apiErrorViewData)
                    }

                    ErrorViewButton.CUSTOM_ACTION -> {
                        onCustomActionClick(apiErrorViewData)
                    }
                }
            }
        ) {
            BasicText(
                text = it,
                maxLines = 1,
                autoSize = TextAutoSize.StepBased(
                    minFontSize = 4.sp,
                    maxFontSize = 16.sp
                ),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
//        ButtonComponent(
//            text = it,
//            buttonStyle = SecondaryButtonStyle.copy(
//                backgroundColor = MaterialTheme.colorScheme.surface
//            ),
//            onClick = {
//                when (buttonType) {
//                    ErrorViewButton.SETTINGS -> {
//                        onSettingsClick(apiErrorViewData)
//                    }
//
//                    ErrorViewButton.TRY_AGAIN -> {
//                        onTryAgainClick(apiErrorViewData)
//                    }
//
//                    ErrorViewButton.CLOSE -> {
//                        onCloseClick(apiErrorViewData)
//                    }
//
//                    ErrorViewButton.CUSTOM_ACTION -> {
//                        onCustomActionClick(apiErrorViewData)
//                    }
//                }
//            }
//        )
    }
}

@Preview
@Composable
private fun ErrorViewComponentPreview() {
    val context = LocalContext.current

    ErrorViewComponent(
        apiErrorVD = APIErrorViewData(
            errorTitle = "Error",
            errorMessage = stringResource(id = R.string.default_network_error_message),
            errorId = "23k23-12332-3kj234-ad23234",
            showTryAgain = true,
            showClose = true,
            showSetting = true,
            showCustomAction = true,
            customActionButtonText = "Custom Action"
        ),
        onCloseClick = {
            Toast.makeText(context, "Close button clicked", Toast.LENGTH_SHORT).show()
        },
        onCustomActionClick = {
            Toast.makeText(context, "Custom Action button clicked", Toast.LENGTH_SHORT).show()
        },
        onSettingsClick = {
            Toast.makeText(context, "Settings button clicked", Toast.LENGTH_SHORT).show()
        },
        onTryAgainClick = {
            Toast.makeText(context, "Try Again button clicked", Toast.LENGTH_SHORT).show()
        },
        errorIcon = {
            Icon(
                modifier = Modifier.size(60.dp),
                imageVector = Icons.Default.ErrorOutline,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
        }
    )
}