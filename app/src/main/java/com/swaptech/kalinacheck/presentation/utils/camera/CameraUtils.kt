package com.swaptech.kalinacheck.presentation.utils.camera

import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import java.util.concurrent.Executor

fun takePhoto(
    imageCapture: ImageCapture,
    executor: Executor,
    onImageCaptured: (ImageProxy) -> Unit,
) {
    imageCapture.takePicture(
        executor,
        object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)
                onImageCaptured(image)
            }
        }
    )
}
