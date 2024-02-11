package ru.glebik.feature.home.internal.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.glebik.core.designsystem.theme.AppTheme
import ru.glebik.core.widget.ErrorView
import ru.glebik.core.widget.LoadingView
import ru.glebik.core.widget.common.BaseAsyncImage
import ru.glebik.core.widget.common.BaseSurface
import ru.glebik.feature.home.internal.presentation.detail.viewmodel.DetailScreenModel
import ru.glebik.feature.home.internal.presentation.detail.viewmodel.DetailStore


data class DetailScreen(
    val id: Int
) : Screen {

    @Composable
    override fun Content() {
        DetailScreenImpl()
    }

    @Composable
    private fun DetailScreenImpl(
        viewModel: DetailScreenModel = getScreenModel()
    ) {
        val navigator = LocalNavigator.currentOrThrow
        val state by viewModel.state.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            viewModel.load(id)
        }

        //Картинка перекрывает statusbar, но из-за этого съезжает вся верстка, поэтому решил выключить
//        val systemUiController = rememberSystemUiController()
//        SideEffect {
//            systemUiController.setStatusBarColor(color = Color.Transparent)
//        }

        if (state.isLoading)
            LoadingView()
        else if (state.isError)
            ErrorView { viewModel.load(id) }
        else
            DetailView(
                state = state,
                navigator::pop,
            )
    }

    @Composable
    private fun DetailView(
        state: DetailStore.State,
        onBackClick: () -> Unit,
    ) {
        BaseSurface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                )
                {
                    AnimeItemWithDesc(state = state)
                }
            }
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.padding(vertical = AppTheme.padding.base)
            ) {
                Icon(
                    painterResource(id = ru.glebik.core.widget.R.drawable.ic_back),
                    "back",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }

    @Composable
    fun AnimeItemWithDesc(state: DetailStore.State) {
        val item = state.film

        if (item != null) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                BaseAsyncImage(
                    data = item.posterUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = AppTheme.padding.basePlus)
                ) {
                    Spacer(modifier = Modifier.padding(vertical = AppTheme.padding.small))
                    Text(
                        text = item.nameRu ?: item.nameOriginal ?: "",
                        style = AppTheme.typography.headerBold,
                    )
                    Spacer(modifier = Modifier.padding(vertical = AppTheme.padding.small))
                    Text(
                        text = item.description ?: "-",
                        style = AppTheme.typography.hint,
                    )
                    Spacer(modifier = Modifier.padding(vertical = AppTheme.padding.tiny))
                    DescItem(
                        key = "Жанры: ",
                        value = item.genres?.joinToString(separator = ", ") ?: ""
                    )
                    Spacer(modifier = Modifier.padding(vertical = AppTheme.padding.tiny))
                    DescItem(
                        key = "Страны: ",
                        value = item.countries?.joinToString(separator = ", ") ?: ""
                    )
                }
            }
        }
    }

    @Composable
    private fun DescItem(
        key: String,
        value: String,
    ) {
        Row {
            Text(text = key, style = AppTheme.typography.hintBold)
            Text(text = value, style = AppTheme.typography.hint)
        }
    }
}