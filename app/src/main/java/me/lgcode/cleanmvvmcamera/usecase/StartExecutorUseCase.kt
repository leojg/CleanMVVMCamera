package me.lgcode.testfeatures.cleanarch.usecase

import me.lgcode.testfeatures.cleanarch.util.ExecutorManager
import java.util.concurrent.ExecutorService

class StartExecutorUseCase(
    val executorManager: ExecutorManager
) {
    fun run() {
        executorManager.startExecutor()
    }
}