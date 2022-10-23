package com.swaptech.kalinacheck.presentation.screens.camera

import android.Manifest
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.swaptech.kalinacheck.presentation.utils.CameraView
import com.swaptech.kalinacheck.presentation.utils.ext.decodeBitmap
import com.swaptech.kalinacheck.presentation.utils.ext.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

@OptIn(ExperimentalPermissionsApi::class)
@androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
@Composable
fun CameraScreen(
    onImageCaptured: (Bitmap) -> Unit
) {
    val permissionState = rememberPermissionState(Manifest.permission.CAMERA)
    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }
    val executor = Dispatchers.IO.asExecutor()
    Surface {
        if (permissionState.status.isGranted) {
            CameraView(
                modifier = Modifier.fillMaxSize(),
                executor = executor,
                onImageCaptured = { proxy ->
                    Log.d("OKOK", "${proxy.format}")
                    proxy.decodeBitmap()?.let {
                        onImageCaptured(it)
                    } ?: proxy.image?.let {
                        onImageCaptured(it.toBitmap())
                    }

                },
            )
        } else {
            Text(text = "Please, grant camera permission")
        }
    }
}
