package me.lgcode.cleanmvvmcamera.fragment

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import me.lgcode.cameracore.manager.IOManager
import me.lgcode.cleanmvvmcamera.databinding.CameraFragmentBinding
import me.lgcode.cameracore.manager.CameraProviderManager
import me.lgcode.cameracore.manager.PreferencesManager
import me.lgcode.cameracore.usecase.*
import me.lgcode.cleanmvvmcamera.model.CameraProviderModel
import me.lgcode.cleanmvvmcamera.viewmodel.CameraViewModel
import me.lgcode.cameracore.manager.ExecutorManager
import me.lgcode.cameracore.manager.PermissionManager

@AndroidEntryPoint
class CameraFragment: Fragment() {

    lateinit var binding: CameraFragmentBinding
    val viewModel: CameraViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = CameraFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.takePictureButton.setOnClickListener {
            viewModel.takePicture()
        }
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            setupObservers()
            viewModel.setupCamera(binding.cameraPreviewview.display)
        }, 1000)
    }

    fun setupObservers() {
        viewModel.cameraProviderModelLiveData.observe(this, Observer {
            startCamera(it)
        })
        viewModel.pictureReadyLiveData.observe(this, Observer {
            showPicture(it)
        })
    }

    fun startCamera(cameraProviderModel: CameraProviderModel) {
        val cameraSelector = viewModel.getCameraSelector()

        try {
            cameraProviderModel.cameraProvider?.unbindAll()
            cameraProviderModel.cameraProvider?.bindToLifecycle(this, cameraSelector, cameraProviderModel.preview, cameraProviderModel.imageCapture)
            cameraProviderModel.preview?.setSurfaceProvider(binding.cameraPreviewview.surfaceProvider)
        } catch (e: Exception) {
            Log.e(CameraFragment::javaClass.name, "Use case binding failed", e)
        }
    }

    fun showPicture(pictureUri: Uri?) {
        Log.d("le picture", pictureUri?.path ?: "no uri :(")
    }

    override fun onStart() {
        super.onStart()
        viewModel.startExecutor()
    }

    override fun onStop() {
        viewModel.stopExecutor()
        super.onStop()
    }

    companion object {
        fun newInstance(): CameraFragment {
            return CameraFragment()
        }
    }

}