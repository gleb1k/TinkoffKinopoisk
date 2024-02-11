package ru.glebik.core.widget.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ru.glebik.core.designsystem.theme.AppTheme
import ru.glebik.core.widget.R

@Composable
fun SearchTextField(
    value: String = "",
    onValueChange: (String) -> Unit,
    onSearchClick: (String) -> Unit,
    onBackClick: (Boolean) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    var showClearIcon by rememberSaveable { mutableStateOf(false) }

    if (value.isEmpty()) {
        showClearIcon = false
    } else if (value.isNotEmpty()) {
        showClearIcon = true
    }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = "Поиск",
                style = AppTheme.typography.hint
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClick(value)
                focusManager.clearFocus()
            }
        ),
        leadingIcon = {
            Icon(
                painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBackClick(false) }
            )

        },
        trailingIcon = {
            if (showClearIcon) {
                IconButton(onClick = {
                    onValueChange("")
                    onSearchClick("")
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = "Clear",
                        tint = AppTheme.colors.primary
                    )
                }
            }
        },
        shape = RoundedCornerShape(18.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = AppTheme.colors.background,
            unfocusedContainerColor = AppTheme.colors.background,
            disabledContainerColor = AppTheme.colors.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
    )

}