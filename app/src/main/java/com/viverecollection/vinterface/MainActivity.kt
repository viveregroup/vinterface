package com.viverecollection.vinterface

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.viverecollection.jetinferface.ui.theme.AppTheme
import com.viverecollection.jetinferface.ui.theme.DarkTheme
import com.viverecollection.jetinferface.ui.theme.LightTheme
import com.viverecollection.vinterface.ui.RefreshableScreenTest


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val content : @Composable ()->Unit = { RefreshableScreenTest() }
            AppTheme(
                darkTheme = { DarkTheme(content = content) },
                lightTheme = { LightTheme(content = content) }
            )
        }
    }
}