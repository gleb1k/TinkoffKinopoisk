package ru.glebik.core.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.glebik.core.widget.common.BaseSurface
import ru.glebik.core.widget.common.PrimaryTextButton


@Composable
fun NotFoundView(
    onRetryClick: () -> Unit
) {
    BaseSurface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PrimaryTextButton(modifier = Modifier.height(56.dp), text = "Не найдено", onRetryClick)
        }
    }
}