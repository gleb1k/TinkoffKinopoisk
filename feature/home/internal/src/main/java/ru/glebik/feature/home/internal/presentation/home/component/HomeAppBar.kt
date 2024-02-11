package ru.glebik.feature.home.internal.presentation.home.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import ru.glebik.core.designsystem.theme.AppTheme
import ru.glebik.core.widget.common.SearchTextField
import ru.glebik.feature.home.internal.presentation.home.viewmodel.HomeStore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    state: HomeStore.State,
    onQuerySearchChange: (String) -> Unit,
    onSearchClick: (String) -> Unit,
    //жижа
    enableSearch: (Boolean) -> Unit
) {
    TopAppBar(
        title = {
            if (state.isFavoriteType)
                Text(

                    "Избранное",
                    style = AppTheme.typography.topBar
                )
            else
                Text(
                    "Популярное",
                    style = AppTheme.typography.topBar
                )
        },
        actions = {
            if (state.isSearchEnable) {
                SearchTextField(
                    value = state.querySearch,
                    onValueChange = onQuerySearchChange,
                    onSearchClick = onSearchClick,
                    onBackClick = enableSearch
                )
            } else {
                IconButton(onClick = {
                    enableSearch(true)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = AppTheme.colors.primary
                    )
                }
            }

        },
        colors = TopAppBarColors(
            containerColor = AppTheme.colors.background,
            scrolledContainerColor = AppTheme.colors.background,
            navigationIconContentColor = AppTheme.colors.background,
            titleContentColor = AppTheme.colors.background,
            actionIconContentColor = AppTheme.colors.background,
        )
    )
}