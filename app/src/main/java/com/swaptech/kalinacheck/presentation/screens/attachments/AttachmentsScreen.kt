package com.swaptech.kalinacheck.presentation.screens.attachments

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrowseGallery
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swaptech.kalinacheck.R
import com.swaptech.kalinacheck.presentation.screens.camera.CameraScreen
import com.swaptech.kalinacheck.presentation.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AttachmentsScreen(
    viewModel: AttachmentsScreenViewModel,
    onTakePhotoButtonClick: () -> Unit,
    onBack: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()
    var bitmap: Bitmap? by remember {
        mutableStateOf(null)
    }
    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetState = sheetState,
        sheetContent = {
            BottomSheet(
                onTakePhotoButtonClick = onTakePhotoButtonClick,
                onPickFromGalleryButtonClick = {  }
            )
        }
    ) {
        if(uiState.showCamera) {
            CameraScreen(
                onImageCaptured = {
                    bitmap = it
                    viewModel.hideCamera()
                }
            )
        }  else {
            bitmap?.let {
                Image(bitmap = it.asImageBitmap(), contentDescription = null)
            }
            LazyColumn {
                item {
                    Column {
                        RevisionInfoItem(
                            revisionName = "Проверка №956846",
                            revisionAddress = "г. Кемерово, Волгоградская ул. 42",
                            revisionExpiredDate = "31.12.2022 15:00"
                        )
                        RevisionPhotosItem(
                            onDeletePhotoItemClick = { /*TODO*/ },
                            onActionButtonClick = {
                                scope.launch {
                                    sheetState.show()
                                }
                            }
                        )
                        RevisionAudioFilesItem(
                            onActionButtonClick = { },
                            onRemoveAudiFileButtonClick = { }
                        )
                    }
                }
            }
        }
    }
    BackHandler(
        onBack = {
            onBack()
            if (sheetState.isVisible) {
                scope.launch {
                    sheetState.hide()
                }
            }
        }
    )
}

@Composable
fun RevisionInfoItem(
    revisionName: String,
    revisionAddress: String,
    revisionExpiredDate: String
) {
    AttachmentsItem(
        title = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Light, fontSize = 16.sp)) {
                append(stringResource(R.string.revision))
            }
            append(" ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Medium, fontSize = 16.sp)) {
                append(revisionName)
            }
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RevisionTextInfo(
                title = stringResource(R.string.revision_address),
                info = revisionAddress,
                titleFontSize = 16.sp,
                infoFontSize = 16.sp
            )
            RevisionTextInfo(
                title = stringResource(R.string.revision_expired_date_title),
                info = revisionExpiredDate,
                titleFontSize = 16.sp,
                infoFontSize = 16.sp
            )
        }
    }
}

@Composable
fun RevisionPhotosItem(
    onDeletePhotoItemClick: () -> Unit,
    onActionButtonClick: () -> Unit
) {
    AttachmentsItem(
        title = stringResource(R.string.photo_title)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            LazyHorizontalGrid(
                modifier = Modifier.height(112.dp),
                rows = GridCells.Fixed(2)
            ) {
                repeat(12) {
                    item {
                        ImageItem(
                            modifier = Modifier.padding(3.dp),
                            imageUrl = "",
                            photoAction = {
                                ShapedIconButton(
                                    modifier = Modifier.align(Alignment.Center),
                                    imageVector = Icons.Filled.Close,
                                    onCLick = onDeletePhotoItemClick
                                )
                            }
                        )
                    }
                }
            }
            KalinaCheckButton(
                modifier = Modifier.height(35.dp),
                text = stringResource(R.string.add),
                onCLick = onActionButtonClick
            )
        }
    }
}

@Composable
fun RevisionAudioFilesItem(
    onActionButtonClick: () -> Unit,
    onRemoveAudiFileButtonClick: () -> Unit
) {
    AttachmentsItem(
        title = stringResource(R.string.audio_files_title)
    ) {
        Column {
            AudioItem(
                audioFilename = "audio_45246",
                action = {
                    IconButton(
                        onClick = onRemoveAudiFileButtonClick
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            tint = Color.White,
                            contentDescription = null
                        )
                    }
                }
            )
            KalinaCheckButton(
                modifier = Modifier.height(35.dp),
                text = stringResource(R.string.add),
                onCLick = onActionButtonClick
            )
        }
    }
}

@Composable
fun BottomSheet(
    onTakePhotoButtonClick: () -> Unit,
    onPickFromGalleryButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onTakePhotoButtonClick)
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.PhotoCamera,
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.take_a_photo)
            )
        }
        Row(
            modifier = Modifier
                .clickable(onClick = onPickFromGalleryButtonClick)
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Image,
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.pick_from_gallery)
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun BottomSheet_Preview() {
    BottomSheet(
        onTakePhotoButtonClick = {

        },
        onPickFromGalleryButtonClick = {

        }
    )
}
