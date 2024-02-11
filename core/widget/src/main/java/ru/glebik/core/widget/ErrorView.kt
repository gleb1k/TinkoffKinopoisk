package ru.glebik.core.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.glebik.core.designsystem.theme.AppTheme
import ru.glebik.core.widget.common.BaseSurface
import ru.glebik.core.widget.common.PrimaryTextButton

@Composable
fun ErrorView(
    onRetryClick: () -> Unit
) {
    BaseSurface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = "Error",
                tint = Color.Unspecified,
                modifier = Modifier.size(100.dp)
            )
            Text(
                "Произошла ошибка при загрузке данных, проверьте подключение к сети",
                color = AppTheme.colors.primary,
                style = AppTheme.typography.body,
                modifier = Modifier.padding(horizontal = AppTheme.padding.basePlus),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(vertical = AppTheme.padding.base))
            PrimaryTextButton(modifier = Modifier.height(56.dp), text = "Повторить", onRetryClick)
        }
    }
}