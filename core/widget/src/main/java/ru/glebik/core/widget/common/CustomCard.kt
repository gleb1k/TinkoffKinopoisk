package ru.glebik.core.widget.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ru.glebik.core.designsystem.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomCard(
    onSmallClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    val mContext = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(112.dp)
            .shadow(elevation = 4.dp, AppTheme.cornerShape.medium)
            .background(AppTheme.colors.background)
            .clip(AppTheme.cornerShape.medium)
            .combinedClickable(
                interactionSource = interactionSource,
                indication = rememberRipple(),
                onLongClick = onLongClick,
                onClick = onSmallClick
            ),
    ) {
        content()
    }
}