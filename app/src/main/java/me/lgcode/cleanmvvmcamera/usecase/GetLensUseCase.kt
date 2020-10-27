package me.lgcode.cleanmvvmcamera.usecase

import androidx.camera.core.CameraSelector
import me.lgcode.cleanmvvmcamera.manager.PreferencesManager

class GetLensUseCase(val preferencesManager: PreferencesManager) {

    fun run(): Int {
        return preferencesManager.getLensFacing()
    }

}