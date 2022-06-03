package com.viverecollection.vinterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.viverecollection.vinterface.ui.RefreshableScreenTest
import id.co.vivere.ui.theme.AppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                RefreshableScreenTest()
            }
        }
    }
}