package me.lgcode.cameracore.usecase

import android.view.Display
import me.lgcode.cameracore.manager.CameraProviderManager
import me.lgcode.cleanmvvmcamera.model.CameraProviderModel
import javax.inject.Inject

class SetupCameraUseCase @Inject constructor(
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