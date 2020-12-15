package me.lgcode.cameracore.manager

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class ExecutorManager @Inject constructor() {

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