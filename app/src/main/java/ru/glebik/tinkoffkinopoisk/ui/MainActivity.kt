package ru.glebik.tinkoffkinopoisk.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import ru.glebik.core.designsystem.settings.LocalSettingsEventBus
import ru.glebik.core.designsystem.settings.SettingsEventBus
import ru.glebik.core.designsystem.theme.AppTheme
import ru.glebik.feature.home.internal.presentation.home.HomeScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        setContent {
            val settingsEventBus = remember { SettingsEventBus() }

            val currentSettings = settingsEventBus.currentSettings.collectAsState().value

            AppTheme(
                currentSettings.isDarkMode
            ) {
                CompositionLocalProvider(
                    LocalSettingsEventBus provides settingsEventBus
                ) {
                    Navigator(
                        HomeScreen
                    )
                }
            }
        }

    }
}



