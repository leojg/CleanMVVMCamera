package me.lgcode.cameracore.usecase

import me.lgcode.cameracore.manager.ExecutorManager
import javax.inject.Inject

class StopExecutorUseCase @Inject constructor(
    val executorManager: ExecutorManager
) {
    fun run() {
        executorManager.stopExecutor()
    }
}