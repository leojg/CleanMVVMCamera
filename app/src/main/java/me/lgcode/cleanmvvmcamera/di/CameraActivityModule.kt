package me.lgcode.cleanmvvmcamera.di

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import me.lgcode.cameracore.manager.*
import me.lgcode.cameracore.usecase.*
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object CameraActivityModule {

    @Provides
    fun provideExecutorManager() = ExecutorManager()

    @Provides
    fun provideGetExecutorUseCase(executorManager: ExecutorManager)
            = GetExecutorUseCase(executorManager)

    @Provides
    fun provideStartExecutorUseCase(executorManager: ExecutorManager)
            = StartExecutorUseCase(executorManager)

    @Provides
    fun provideStopExecutorUseCase(executorManager: ExecutorManager)
            = StopExecutorUseCase(executorManager)

    @Provides
    fun provideGetLensUseCase(preferencesManager: PreferencesManager)
            = GetLensUseCase(preferencesManager)

    @Provides
    fun provideSetupCameraUseCase(
        cameraProviderManager: CameraProviderManager,
        getLensUseCase: GetLensUseCase
    ) = SetupCameraUseCase(cameraProviderManager, getLensUseCase)

    @Provides
    fun provideTakePictureUseCase(
        getLensUseCase: GetLensUseCase,
        getExecutorUseCase: GetExecutorUseCase,
        ioManager: IOManager
    ) = TakePictureUseCase(getLensUseCase, getExecutorUseCase, ioManager)

    @Provides
    fun providePermissionManager(@ActivityContext context: Context) = PermissionManager(context as Activity)

    @Provides
    fun provideCameraProviderManager(@ApplicationContext context: Context)
            = CameraProviderManager(context)

    @Provides
    fun provideIOManager(@ApplicationContext context: Context)
            = IOManager(context)

    @Provides
    fun providePreferencesManager(@ApplicationContext context: Context)
            = PreferencesManager(context, "testFile") //TODO: Change this

}