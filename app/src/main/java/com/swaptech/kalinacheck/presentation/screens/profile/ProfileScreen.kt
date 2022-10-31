package com.swaptech.kalinacheck.presentation.screens.profile

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.swaptech.kalinacheck.R
import com.swaptech.kalinacheck.presentation.theme.DarkGray
import com.swaptech.kalinacheck.presentation.utils.ActiveRevision

@Composable
fun ProfileScreen(
    onSeeAttachmentsClick: () -> Unit,
    onFormButtonClick: () -> Unit
) {
    val scores = 90
    val haveActiveRevision = true
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Данил",
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = stringResource(R.string.your_phone_number),
            fontWeight = FontWeight.Normal,
            color = if (isSystemInDarkTheme()) Color.LightGray else DarkGray
        )
        Text(
            text = "+7(999)-999-99-99",
            fontWeight = FontWeight.Medium
        )
        Text(
            text = stringResource(R.string.accumulated_scores),
            fontWeight = FontWeight.Normal,
            color = if (isSystemInDarkTheme()) Color.LightGray else DarkGray
        )
        Text(
            text = "$scores",
            fontWeight = FontWeight.Medium
        )
        if (haveActiveRevision) {
            ActiveRevision(
                revisionName = "Проверка №847802746874",
                revisionExpiredDate = "31.12.22 11:00",
                onSeeAttachmentsClick = onSeeAttachmentsClick,
                onFormButtonClick = onFormButtonClick
            )
        }
    }
}
