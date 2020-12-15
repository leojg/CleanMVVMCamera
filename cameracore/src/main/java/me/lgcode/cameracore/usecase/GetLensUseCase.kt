package me.lgcode.cameracore.usecase

import me.lgcode.cameracore.manager.PreferencesManager

class GetLensUseCase(val preferencesManager: PreferencesManager) {

    fun run(): Int {
        return preferencesManager.getLensFacing()
    }

}