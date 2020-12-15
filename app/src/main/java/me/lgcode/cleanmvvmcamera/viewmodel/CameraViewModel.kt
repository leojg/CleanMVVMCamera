package me.lgcode.cleanmvvmcamera.viewmodel

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.view.Display
import androidx.camera.core.CameraSelector
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import me.lgcode.cleanmvvmcamera.model.CameraProviderModel
import me.lgcode.cameracore.usecase.GetLensUseCase
import me.lgcode.cameracore.usecase.SetupCameraUseCase
import me.lgcode.cameracore.usecase.StopExecutorUseCase
import me.lgcode.cameracore.usecase.TakePictureUseCase
import me.lgcode.cameracore.usecase.StartExecutorUseCase
import me.lgcode.cameracore.manager.PermissionManager

class CameraViewModel @ViewModelInject constructor(
    val setupCameraUseCase: SetupCameraUseCase,
    val takePictureUseCase: TakePictureUseCase,
    val getLensUseCase: GetLensUseCase,
    val permissionsManager: PermissionManager,
    val startExecutorUseCase: StartExecutorUseCase,
    val stopExecutorUseCase: StopExecutorUseCase
): ViewModel(), LifecycleObserver {

    val cameraProviderModelLiveData = MutableLiveData<CameraProviderModel>()
    val pictureReadyLiveData = MutableLiveData<Uri?>()
    lateinit var display: Display

    fun setupCamera(display: Display) {
        this.display = display
        if (hasCameraPermissions()) {
            runSetupCamera()
        }
    }

    private fun runSetupCamera()  {
        viewModelScope.launch {
            cameraProviderModelLiveData.postValue(setupCameraUseCase.run(display))
        }
    }

    fun takePicture() {
        cameraProviderModelLiveData.value?.imageCapture?.let {
            viewModelScope.launch {
                val uri = takePictureUseCase.run(it)
                pictureReadyLiveData.postValue(uri)
            }
        }
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            OPEN_CAMERA_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    runSetupCamera()
                } else {
                    //TODO: notify the view
                    //Toast.makeText(this, "W U NO GIB CAM", Toast.LENGTH_LONG).show()
                }
            }

            else -> {
            }
        }
    }

    fun getCameraSelector(): CameraSelector {
        return CameraSelector.Builder().requireLensFacing(getLensUseCase.run()).build()
    }

    fun hasCameraPermissions() =
        permissionsManager.checkPermissions(listOf(Manifest.permission.CAMERA), CameraViewModel.OPEN_CAMERA_PERMISSION_REQUEST_CODE)

    fun startExecutor() {
        startExecutorUseCase.run()
    }

    fun stopExecutor() {
        stopExecutorUseCase.run()
    }

    companion object {
        const val OPEN_CAMERA_PERMISSION_REQUEST_CODE = 1
    }

}