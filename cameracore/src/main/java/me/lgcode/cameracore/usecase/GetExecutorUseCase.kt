package me.lgcode.cameracore.usecase

import me.lgcode.cameracore.manager.ExecutorManager
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class GetExecutorUseCase @Inject constructor(
    val executorManager: ExecutorManager
) {
    fun run(): ExecutorService = executorManager.getExecutor()
}