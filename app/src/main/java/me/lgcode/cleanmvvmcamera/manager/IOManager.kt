package me.lgcode.testfeatures.cleanarch.util

import android.content.Context
import me.lgcode.cleanmvvmcamera.R
import java.io.File
import java.text.SimpleDateFormat

class IOManager(val context: Context) {

    fun getOutputDirectory(): File {
        //val appContext = context.applicationContext
        val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
            File(it, context.resources.getString(R.string.app_name)).apply { mkdirs() }
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