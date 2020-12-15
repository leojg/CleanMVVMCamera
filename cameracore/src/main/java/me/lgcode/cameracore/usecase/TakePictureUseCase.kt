package me.lgcode.cameracore.usecase

import android.net.Uri
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.Metadata
import androidx.camera.core.ImageCaptureException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import me.lgcode.cameracore.manager.IOManager

class TakePictureUseCase(
    val getLensUseCase: GetLensUseCase,
    val getExecutorUseCase: GetExecutorUseCase,
    val ioManager: IOManager
) {
    suspend fun run(imageCapture: ImageCapture): Uri? {
            return coroutineScope {
                val channel = Channel<Uri?>()
                async { execute(imageCapture, channel) }
                channel.receive()
            }
    }

    private suspend fun execute(imageCapture: ImageCapture, channel: Channel<Uri?>): Uri? {
        val file = IOManager.createFile(ioManager.getOutputDirectory(), IOManager.PHOTO_EXTENSION)

        val metadata = Metadata().apply {
            isReversedHorizontal = getLensUseCase.run() == CameraSelector.LENS_FACING_FRONT
        }

        val outputOptions = ImageCapture.OutputFileOptions.Builder(file)
            .setMetadata(metadata)
            .build()

        var resultUri: Uri? = null

        //TODO: HAY QUE USAR CHANNELS

        imageCapture.takePicture(
            outputOptions, getExecutorUseCase.run(), object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    resultUri = outputFileResults.savedUri ?: Uri.fromFile(file)
                    GlobalScope.launch {
                        channel.send(resultUri)
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    TODO("Not yet implemented")
                    Log.e("si rumpio", exception.message)
                }

            }
        )

        return resultUri
    }

}