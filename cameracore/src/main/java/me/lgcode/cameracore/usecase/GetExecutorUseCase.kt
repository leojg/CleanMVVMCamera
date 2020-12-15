package me.lgcode.cameracore.usecase

import me.lgcode.testfeatures.cleanarch.util.ExecutorManager
import java.util.concurrent.ExecutorService

class GetExecutorUseCase(
    val executorManager: ExecutorManager
) {
    fun run(): ExecutorService = executorManager.getExecutor()
}