package com.swaptech.kalinacheck.presentation.screens.landing

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.swaptech.kalinacheck.R
import com.swaptech.kalinacheck.domain.auth.SendCode
import com.swaptech.kalinacheck.domain.auth.SendName
import com.swaptech.kalinacheck.domain.auth.SendPhone
import com.swaptech.kalinacheck.presentation.utils.Article
import com.swaptech.kalinacheck.presentation.utils.KalinaCheckButton
import com.swaptech.kalinacheck.presentation.utils.KalinaCheckTopAppBar
import com.swaptech.kalinacheck.presentation.view_model.AuthViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun LandingScreen(
    viewModel: LandingScreenViewModel,
    authViewModel: AuthViewModel,
    onNavigateToHome: () -> Unit
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    var authCode by rememberSaveable {
        mutableStateOf(-1)
    }
    var phone by rememberSaveable {
        mutableStateOf("")
    }
    BottomSheetScaffold(
        topBar = {
            KalinaCheckTopAppBar(
                title = stringResource(R.string.app_name)
            )
        },
        scaffoldState = scaffoldState,
        sheetContent = {
            SheetContent(
                enterPhone = uiState.enterPhone,
                enterSMSCode = uiState.enterSMSCode,
                enterName = uiState.enterName,
                remainingSeconds = uiState.retryWaitingTimeRemainingSeconds,
                textFieldText = uiState.bottomSheetTextFieldText,
                onTextFieldTextChange = viewModel::onBottomSheetTextFieldTextChange,
                onActionButtonClick = { enterPhone, enterSMSCode ->
                    when {
                        enterPhone -> {
                            viewModel.sendPhone(
                                SendPhone(uiState.bottomSheetTextFieldText),
                                onSuccess = {
                                    authCode = it
                                    viewModel.setBottomSheetText(it.toString())
                                },
                                onFail = { code, message ->
                                    if (code == 404) {
                                        Log.d("okokok", message)
                                        authCode = message.toInt()
                                        viewModel.setBottomSheetText(message)
                                    }
                                }
                            )
                            phone = uiState.bottomSheetTextFieldText
                            viewModel.clearBottomSheetText()
                            viewModel.startTimer()
                            viewModel.showEnterSMSCodeSheet()
                        }
                        enterSMSCode -> {
                            viewModel.sendCode(
                                SendCode(phone, authCode),
                                onSuccess = { token ->
                                    authViewModel.saveUserToken(token)
                                    onNavigateToHome()
                                    viewModel.clearBottomSheetText()
                                    viewModel.showEnterName()
                                },
                                onFail = { _, _ ->
                                    viewModel.showEnterName()
                                }
                            )
                        }
                        else -> {
                            viewModel.sendName(
                                SendName(phone, uiState.bottomSheetTextFieldText),
                                onSuccess = { token ->
                                    authViewModel.saveUserToken(token)
                                    onNavigateToHome()
                                },
                                onFail = { _, _ ->
                                }
                            )
                        }
                    }
                },
                onRetryButtonClick = {
                    viewModel.dropTimer()
                    viewModel.startTimer()
                }
            )
        },
        sheetPeekHeight = 0.dp,
        sheetBackgroundColor = Color.DarkGray.copy(alpha = 0.5f),
        sheetContentColor = Color.DarkGray.copy(alpha = 0.5f)
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Article(
                        modifier = Modifier.padding(
                            start = 40.dp, top = 20.dp, bottom = 20.dp
                        ),
                        titleText = "Станьте тайным покупателем уже сегодня!",
                        descriptionText = "Вы сможете лично проверить то, как работают магазины Сети и заработать баллы на карту лояльности"
                    )
                }
                item {
                    Article(
                        modifier = Modifier.padding(
                            start = 40.dp, top = 20.dp, bottom = 20.dp
                        ),
                        titleText = "Сервис и чистота на новом уровне",
                        descriptionText = "Благодаря Вашим проверкам магазины сети Калина-Малина становятся еще чище и приятнее"
                    )
                }
                item {
                    Article(
                        modifier = Modifier.padding(
                            start = 40.dp, top = 20.dp, bottom = 20.dp
                        ),
                        titleText = "Чем больше проверок, тем больше баллов",
                        descriptionText = "Проект Калина-Чек предполагает вознаграждение в виде баллов на карту лояльности за выполнение заданий и заполнение анкет. Баллы вы сможете тратить в магазинах Сети"
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(100.dp))
                }
            }
            KalinaCheckButton(modifier = Modifier
                .padding(12.dp)
                .align(Alignment.BottomCenter),
                text = stringResource(R.string.join),
                onCLick = {
                    scope.launch {
                        if (scaffoldState.bottomSheetState.isCollapsed) {
                            scaffoldState.bottomSheetState.expand()
                        } else {
                            scaffoldState.bottomSheetState.collapse()
                        }
                    }
                })
        }
        LaunchedEffect(scaffoldState.bottomSheetState.isCollapsed) {
            if (scaffoldState.bottomSheetState.isCollapsed) {
                keyboardController?.hide()
            }
        }
    }
}

@Composable
fun SheetContent(
    enterPhone: Boolean,
    enterSMSCode: Boolean,
    enterName: Boolean,
    remainingSeconds: Int,
    textFieldText: String,
    onTextFieldTextChange: (String) -> Unit,
    onActionButtonClick: (enterPhone: Boolean, enterSMSCode: Boolean) -> Unit,
    onRetryButtonClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = textFieldText,
                onValueChange = onTextFieldTextChange,
                keyboardOptions = KeyboardOptions(
                    keyboardType = if(enterName) KeyboardType.Text else KeyboardType.Number
                ),
                label = {
                    Text(
                        text = stringResource(
                            when {
                                enterPhone -> {
                                    R.string.enter_phone_number
                                }
                                enterSMSCode -> {
                                    R.string.enter_sms_code
                                }
                                else -> {
                                    R.string.enter_name
                                }
                            }
                        )
                    )
                })
            if (enterSMSCode) {
                if (remainingSeconds == 0) {
                    IconButton(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = onRetryButtonClick
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Replay, contentDescription = null
                        )
                    }
                } else {
                    Text(
                        text = stringResource(
                            id = R.string.enter_sms_code_again, formatArgs = arrayOf(
                                if (remainingSeconds < 10) {
                                    "0:0$remainingSeconds"
                                } else {
                                    "0:$remainingSeconds"
                                }
                            )
                        )
                    )
                }
            }
            KalinaCheckButton(
                text = when {
                    enterPhone -> {
                        stringResource(R.string.receive_sms_code)
                    }
                    enterSMSCode -> {
                        "Продолжить"
                    }
                    else -> {
                        stringResource(R.string.enter)
                    }
                },
                enabled = remainingSeconds == 0 || enterPhone, onCLick = {
                    onActionButtonClick(enterPhone, enterSMSCode)
                }
            )
        }
    }
}
