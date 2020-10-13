package me.lgcode.cleanmvvmcamera.usecase

import android.view.Display
import me.lgcode.cleanmvvmcamera.manager.CameraProviderManager
import me.lgcode.cleanmvvmcamera.model.CameraProviderModel

class SetupCameraUseCase(
    val cameraProvider: CameraProviderManager,
    val getLensUseCase: GetLensUseCase
) {
    fun run(display: Display): CameraProviderModel? {
        return cameraProvider.startCameraProvider(display).apply {
            this?.lensFacing = getLensUseCase.run()
            this
        }
    }


}