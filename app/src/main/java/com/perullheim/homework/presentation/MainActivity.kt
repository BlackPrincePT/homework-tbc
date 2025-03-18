package com.perullheim.homework.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.perullheim.homework.presentation.equipment.screen.EquipmentScreen
import dagger.hilt.android.AndroidEntryPoint
import ge.tkgroup.myapplication.ui.theme.MyApplicationTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                EquipmentScreen()
            }
        }
    }
}