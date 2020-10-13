package me.lgcode.testfeatures.cleanarch.util

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ExecutorManager {

    private lateinit var cameraExecutor: ExecutorService

    fun startExecutor() {
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    fun getExecutor(): ExecutorService {
        if (::cameraExecutor.isInitialized) {
            startExecutor()
        }
        return cameraExecutor
    }

    fun stopExecutor() {
        cameraExecutor.shutdown()
    }

}