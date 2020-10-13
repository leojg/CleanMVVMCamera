package me.lgcode.cleanmvvmcamera.usecase

import androidx.camera.core.CameraSelector

class GetLensUseCase {

    fun run(): Int {
        return CameraSelector.LENS_FACING_BACK
    }

}