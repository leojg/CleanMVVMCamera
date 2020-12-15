package me.lgcode.cameracore.manager

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import javax.inject.Inject

class IOManager @Inject constructor(val context: Context) {

    fun getOutputDirectory(): File {
        //TODO: Move path to a config file
        val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
            File(it, "my_temp_path").apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir
    }

    companion object {
        const val PHOTO_EXTENSION = ".jpg"

        fun createFile(baseFolder: File, extension: String) =
            File(
                baseFolder,
                SimpleDateFormat.getDateTimeInstance()
                    .format(System.currentTimeMillis())
                    .plus(extension)
            )

    }

}