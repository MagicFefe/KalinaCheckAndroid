package com.swaptech.kalinacheck.presentation.screens.recorder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.swaptech.kalinacheck.R
import com.swaptech.kalinacheck.presentation.utils.KalinaCheckButton
import com.swaptech.kalinacheck.presentation.utils.ShapedIconButton

@Composable
fun RecorderScreen(
    viewModel: RecorderScreenViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShapedIconButton(
            modifier = Modifier.size(50.dp),
            imageVector = Icons.Filled.Mic,
            onCLick = { /*TODO*/ },
            backgroundColor = MaterialTheme.colors.primary
        )
        //TODO: Implement timer
        Text(text = "")
        Spacer(modifier = Modifier.weight(1f))
        KalinaCheckButton(
            text = stringResource(R.string.send),
            onCLick = {

            }
        )
    }
}
