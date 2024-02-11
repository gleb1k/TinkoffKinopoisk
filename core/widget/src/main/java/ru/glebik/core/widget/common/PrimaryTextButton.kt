package ru.glebik.core.widget.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.glebik.core.designsystem.theme.AppTheme

@Composable
fun PrimaryTextButton(
    modifier: Modifier,
    text: String = "",
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = AppTheme.cornerShape.large,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.onPrimary
        )
    ) {
        Text(
            text = text,
            style = AppTheme.typography.onButtonPrimary,
            modifier = Modifier
                .padding(horizontal = AppTheme.padding.base)
        )
    }
}