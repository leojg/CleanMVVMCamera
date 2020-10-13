package me.lgcode.cleanmvvmcamera.usecase

import me.lgcode.testfeatures.cleanarch.util.ExecutorManager

class StopExecutorUseCase(
    val executorManager: ExecutorManager
) {
    fun run() {
        executorManager.stopExecutor()
    }
}