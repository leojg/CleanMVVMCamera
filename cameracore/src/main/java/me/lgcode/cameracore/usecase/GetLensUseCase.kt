package me.lgcode.cameracore.usecase

import me.lgcode.cameracore.manager.PreferencesManager
import javax.inject.Inject

class GetLensUseCase @Inject constructor(val preferencesManager: PreferencesManager) {

    fun run(): Int {
        return preferencesManager.getLensFacing()
    }

}