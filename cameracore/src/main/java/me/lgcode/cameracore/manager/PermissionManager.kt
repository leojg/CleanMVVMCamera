package me.lgcode.testfeatures.cleanarch.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionManager(val activity: Activity) {

    fun checkPermissions(permissions: List<String>, requestCode: Int): Boolean {
        val missingPermissions = ArrayList<String>()
        for (permission in permissions) {
            if (
                ContextCompat.checkSelfPermission(
                    activity,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                missingPermissions.add(permission)
            }
        }
        return if (missingPermissions.isEmpty()) {
            true
        } else {
            ActivityCompat.requestPermissions(
                activity,
                missingPermissions.toTypedArray(), requestCode
            )
            false
        }
    }

}