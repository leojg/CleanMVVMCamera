package me.lgcode.cleanmvvmcamera.manager

import android.content.Context
import android.view.Display
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import me.lgcode.cleanmvvmcamera.model.CameraProviderModel
import me.lgcode.testfeatures.cleanarch.util.ScreenUtils

class CameraProviderManager(
    val context: Context
) {

    fun startCameraProvider(display: Display): CameraProviderModel? {
        try {
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

            val aspectRatio = ScreenUtils.getAspectRatio(display)

            val preview = Preview.Builder()
                .setTargetAspectRatio(aspectRatio)
                .setTargetRotation(display.rotation)
                .build()

            val imageCapture = ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .setTargetAspectRatio(aspectRatio)
                .setTargetRotation(display.rotation)
                .build()

            return CameraProviderModel(
                cameraProvider = cameraProviderFuture.get(),
                preview = preview,
                imageCapture = imageCapture
            )
        } catch (e: Exception) {
            e.printStackTrace()
        } catch (e: Error) {
            e.printStackTrace()
        }
        return null
    }

}