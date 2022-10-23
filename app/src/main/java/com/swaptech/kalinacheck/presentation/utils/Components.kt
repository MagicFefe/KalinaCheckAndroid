package com.swaptech.kalinacheck.presentation.utils

import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.sharp.Lens
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.swaptech.kalinacheck.R
import com.swaptech.kalinacheck.presentation.theme.*
import com.swaptech.kalinacheck.presentation.utils.camera.takePhoto
import com.swaptech.kalinacheck.presentation.utils.ext.getCameraProvider
import java.io.File
import java.util.concurrent.Executor

@Composable
fun KalinaCheckTopAppBar(
    title: String,
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        elevation = elevation,
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.onSurface
            )
        }
    )
}

@Composable
fun Article(
    modifier: Modifier = Modifier,
    titleText: String,
    descriptionText: String
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = titleText,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = descriptionText,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = DarkGray
        )
    }
}

@Composable
fun KalinaCheckButton(
    modifier: Modifier = Modifier,
    text: String,
    shape: Shape = RoundedCornerShape(12.dp),
    onCLick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        onClick = onCLick,
        shape = shape,
        enabled = enabled
    ) {
        Text(text = text)
    }
}

@Composable
fun RevisionItem(
    revisionName: String,
    revisionExpiredDate: String,
    onCLick: (() -> Unit)? = null,
    successfulCompleted: Boolean = false,
    isActive: Boolean
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        shape = RoundedCornerShape(40.dp),
        color = Brown
    ) {
        Column(
            modifier = Modifier.padding(17.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = revisionName,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                text = stringResource(R.string.revision_expired_date_title),
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                color = Color.White
            )
            Text(
                text = revisionExpiredDate,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Color.White
            )
            if (isActive) {
                KalinaCheckButton(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .height(35.dp),
                    shape = RoundedCornerShape(10.dp),
                    text = stringResource(R.string.start_revision),
                    onCLick = onCLick ?: { }
                )
            } else {
                val iconColor = if (successfulCompleted) {
                    Green
                } else {
                    Red500
                }
                val icon = if (successfulCompleted) {
                    Icons.Filled.TaskAlt
                } else {
                    Icons.Filled.Block
                }
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = icon,
                    tint = iconColor,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun RevisionItem_Preview() {
    KalinaCheckTheme {
        RevisionItem(
            revisionName = "Проверка №12436464",
            revisionExpiredDate = "31.10.2022 11:00",
            isActive = true
        )
    }
}

@Composable
fun ActiveRevision(
    revisionName: String,
    revisionExpiredDate: String,
    onSeeAttachmentsClick: () -> Unit,
    onFormButtonClick: () -> Unit,
    formFilled: Boolean = false,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        shape = RoundedCornerShape(40.dp),
        color = Brown
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.current_revision),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = Color.White
            )
            Text(
                text = revisionName,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                text = stringResource(R.string.revision_expired_date_title),
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                color = Color.White
            )
            Text(
                text = revisionExpiredDate,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Color.White
            )
            KalinaCheckButton(
                modifier = Modifier
                    .padding(top = 5.dp, start = 10.dp, end = 10.dp)
                    .height(35.dp),
                shape = RoundedCornerShape(10.dp),
                text = stringResource(R.string.see_attachments),
                onCLick = onSeeAttachmentsClick
            )
            KalinaCheckButton(
                modifier = Modifier
                    .padding(top = 5.dp, start = 10.dp, end = 10.dp)
                    .height(35.dp),
                shape = RoundedCornerShape(10.dp),
                text = stringResource(
                    if (formFilled) {
                        R.string.see_form
                    } else {
                        R.string.fill_form
                    }
                ),
                onCLick = onFormButtonClick
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ActiveRevision_Preview() {
    KalinaCheckTheme {
        ActiveRevision(
            revisionName = "Проверка №12436464",
            revisionExpiredDate = "31.10.2022 11:00",
            onSeeAttachmentsClick = { },
            onFormButtonClick = { }
        )
    }
}

@Composable
fun RevisionTextInfo(
    title: String,
    info: String,
    titleFontSize: TextUnit = 12.sp,
    infoFontSize: TextUnit = 12.sp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Light,
            fontSize = titleFontSize,
            color = Color.White
        )
        Text(
            text = info,
            fontWeight = FontWeight.Normal,
            fontSize = infoFontSize,
            color = Color.White
        )
    }
}

@Composable
fun AttachmentsItem(
    title: String,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(40.dp),
        color = Brown
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Light,
                fontSize = 20.sp,
                color = Color.White
            )
            content()
        }
    }
}

@Composable
fun AttachmentsItem(
    title: AnnotatedString,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        shape = RoundedCornerShape(40.dp),
        color = Brown
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Light,
                fontSize = 20.sp,
                color = Color.White
            )
            content()
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun AttachmentsItem_Preview() {
    KalinaCheckTheme {
        AttachmentsItem(
            title = "Test"
        ) {

        }
    }
}

@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    photoAction: @Composable BoxScope.() -> Unit
) {
    val context = LocalContext.current
    Box {
        SubcomposeAsyncImage(
            modifier = modifier,
            model = ImageRequest.Builder(context)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            loading = {
                Box(
                    modifier = Modifier
                        .background(Color.Gray)
                        .size(50.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            error = {
                Box(
                    modifier = Modifier
                        .background(Color.Gray)
                        .size(50.dp)
                ) {
                }
            },
            contentDescription = null
        )
        photoAction()
    }
}

@Composable
fun ShapedIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    tint: Color = Color.White,
    shape: Shape = CircleShape,
    onCLick: () -> Unit,
    backgroundColor: Color = Color.Black.copy(alpha = 0.3f)
) {
    Surface(
        modifier = modifier.clickable(onClick = onCLick),
        color = backgroundColor,
        shape = shape
    ) {
        Icon(
            imageVector = imageVector,
            tint = tint,
            contentDescription = null
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ImageItem_Preview() {
    ShapedIconButton(
        imageVector = Icons.Filled.Close,
        onCLick = { /*TODO*/ }
    )
}

@Composable
fun AudioItem(
    audioFilename: String,
    action: @Composable () -> Unit = { }
) {
    Surface(
        color = Brown
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                Surface(
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center),
                    color = MaterialTheme.colors.primary,
                    shape = CircleShape
                ) {

                }
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center),
                    imageVector = Icons.Filled.Headphones,
                    tint = Color.White,
                    contentDescription = null
                )
            }
            Text(
                text = audioFilename,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            action()
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun AudioItem_Preview() {
    KalinaCheckTheme {
        AudioItem(audioFilename = "test")     {
            Icon(imageVector = Icons.Filled.Close, contentDescription = null)
        }
    }
}

@Composable
fun CameraView(
    modifier: Modifier = Modifier,
    executor: Executor,
    onImageCaptured: (ImageProxy) -> Unit,
) {
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val preview = androidx.camera.core.Preview.Builder().build()
    val previewView = remember { PreviewView(context) }
    val imageCapture: ImageCapture = remember { ImageCapture.Builder().build() }
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(lensFacing)
        .build()
    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )

        preview.setSurfaceProvider(previewView.surfaceProvider)
    }
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier.fillMaxSize()
    ) {
        AndroidView({ previewView }, modifier = Modifier.fillMaxSize())

        IconButton(
            modifier = Modifier.padding(bottom = 20.dp),
            onClick = {
                takePhoto(
                    imageCapture = imageCapture,
                    executor = executor,
                    onImageCaptured = onImageCaptured,
                )
            },
            content = {
                Icon(
                    imageVector = Icons.Sharp.Lens,
                    contentDescription = "Take picture",
                    tint = Color.White,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(1.dp)
                        .border(1.dp, Color.White, CircleShape)
                )
            }
        )
    }
}
