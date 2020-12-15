package me.lgcode.cameracore.usecase

import me.lgcode.cameracore.manager.ExecutorManager
import javax.inject.Inject

class StartExecutorUseCase @Inject constructor(
    val executorManager: ExecutorManager
) {
    fun run() {
        executorManager.startExecutor()
    }
}