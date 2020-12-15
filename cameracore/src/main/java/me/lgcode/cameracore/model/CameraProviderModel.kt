package me.lgcode.cleanmvvmcamera.model

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider

data class CameraProviderModel(
    var cameraProvider: ProcessCameraProvider? = null,
    var lensFacing: Int = CameraSelector.LENS_FACING_BACK,
    var preview: Preview? = null,
    var imageCapture: ImageCapture? = null
)