package com.devs.faceRecognition.app

import android.app.Application
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.devs.faceRecognition.ui.navigation.AppHost
import com.devs.faceRecognition.ui.navigation.Routes
import com.devs.faceRecognition.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import com.devs.faceRecognition.lib.LOG

object MAIN {
    @HiltAndroidApp
    class HiltApp : Application()

    @AndroidEntryPoint
    class AppActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            setContent { AppContent() }
        }

    }

    @Composable
    fun AppContent() = AppTheme(dynamicColors = true, statusBar = true) {
        runCatching { Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) { AppHost(Routes.Home.path) } }
            .onFailure { LOG.e(it, it.message) }.exceptionOrNull()?.printStackTrace()
    }
}



