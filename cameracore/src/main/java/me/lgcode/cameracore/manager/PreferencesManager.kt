package me.lgcode.cameracore.manager

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import androidx.camera.core.CameraSelector

class PreferencesManager(val context: Context, val prefFile: String) {

    fun setLensFacing(value: Int) {
        try {
            context.getSharedPreferences(prefFile, MODE_PRIVATE)
                .edit().putInt(LENS_SHARED_PREFERENCE, value).apply()
        } catch (e: Exception) {
            Log.e(PreferencesManager::class.simpleName, e.message)
        }
    }

    fun getLensFacing(): Int =
        context.getSharedPreferences(prefFile, MODE_PRIVATE)
            .getInt(LENS_SHARED_PREFERENCE, CameraSelector.LENS_FACING_BACK)

    companion object {
        private const val LENS_SHARED_PREFERENCE = "LENS_SHARED_PREFERENCE"
    }

}