package com.swaptech.kalinacheck.presentation.screens.form

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swaptech.kalinacheck.R
import com.swaptech.kalinacheck.presentation.theme.KalinaCheckTheme
import com.swaptech.kalinacheck.presentation.utils.BackHandler
import com.swaptech.kalinacheck.presentation.utils.KalinaCheckButton

@Composable
fun FormScreen(
    tasks: List<String>,
    onBack: () -> Unit
) {
    Surface(
        color = MaterialTheme.colors.surface
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            repeat(15) {
                item {
                    Task(
                        taskOrderNumber = it + 1,
                        info = "Проверка",
                        formType = if (it == 2) FormType.SINGLE_LINE else FormType.MULTI_LINE,
                        text = "",
                        onTextChange = {},
                        onDoneButtonClick = {}
                    )
                }
            }
            item {
                KalinaCheckButton(
                    modifier = Modifier.padding(10.dp),
                    text = stringResource(R.string.send_form),
                    onCLick = {

                    }
                )
            }
        }
    }
    BackHandler(
        onBack = onBack
    )
}

@Composable
fun Task(
    taskOrderNumber: Int,
    info: String,
    formType: FormType,
    text: String,
    onTextChange: (String) -> Unit,
    onDoneButtonClick: () -> Unit,
) {
    val textHintResource = if (formType == FormType.SINGLE_LINE) {
        R.string.enter_short_answer
    } else {
        R.string.enter_full_answer
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = stringResource(
                id = R.string.task_with_number,
                formatArgs = arrayOf("$taskOrderNumber"),
            ),
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
        Text(
            text = info,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(textHintResource)
                )
            },
            value = text,
            onValueChange = onTextChange,
            singleLine = formType == FormType.SINGLE_LINE,
            maxLines = if (formType == FormType.SINGLE_LINE) 1 else 20
        )
        KalinaCheckButton(
            text = stringResource(R.string.done),
            onCLick = onDoneButtonClick
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun Hui() {
    KalinaCheckTheme {
        Task(
            taskOrderNumber = 2,
            info = "Некое задание с мульти селектом",
            formType = FormType.MULTI_LINE,
            text = "",
            onTextChange = {
            },
            onDoneButtonClick = {
            }
        )
    }
}
