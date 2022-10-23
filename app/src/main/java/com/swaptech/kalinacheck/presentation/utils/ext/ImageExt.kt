package com.swaptech.kalinacheck.presentation.utils.ext

import android.graphics.*
import android.media.Image
import androidx.camera.core.ImageProxy
import java.io.ByteArrayOutputStream

fun Image.toBitmap(): Bitmap {
    val yBuffer = planes[0].buffer // Y

    val ySize = yBuffer.remaining()

    val nv21 = ByteArray(ySize)

    yBuffer.get(nv21, 0, ySize)

    val yuvImage = YuvImage(nv21, ImageFormat.NV21, this.width, this.height, null)
    val out = ByteArrayOutputStream()
    yuvImage.compressToJpeg(Rect(0, 0, yuvImage.width, yuvImage.height), 50, out)
    val imageBytes = out.toByteArray()
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}

fun ImageProxy.decodeBitmap(): Bitmap? {
    val buffer = planes[0].buffer
    val bytes = ByteArray(buffer.capacity()).also { buffer.get(it) }
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
}
