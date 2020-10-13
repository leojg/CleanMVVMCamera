package me.lgcode.cleanmvvmcamera.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import me.lgcode.cleanmvvmcamera.R
import me.lgcode.cleanmvvmcamera.databinding.MainActivityBinding
import me.lgcode.cleanmvvmcamera.fragment.CameraFragment

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: MainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, CameraFragment.newInstance())
        fragmentTransaction.commit()
    }

}