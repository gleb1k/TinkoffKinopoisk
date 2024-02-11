package ru.glebik.feature.home.internal.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import ru.glebik.core.designsystem.theme.AppTheme
import ru.glebik.core.navigation.SharedScreen
import ru.glebik.core.widget.ErrorView
import ru.glebik.core.widget.LoadingView
import ru.glebik.core.widget.NotFoundView
import ru.glebik.core.widget.R
import ru.glebik.core.widget.common.BaseSurface
import ru.glebik.core.widget.common.CustomCard
import ru.glebik.core.widget.common.PrimaryTextButton
import ru.glebik.core.widget.common.SecondaryTextButton
import ru.glebik.feature.home.api.model.domain.FilmSmall
import ru.glebik.feature.home.internal.presentation.home.component.HomeAppBar
import ru.glebik.feature.home.internal.presentation.home.viewmodel.HomeScreenModel
import ru.glebik.feature.home.internal.presentation.home.viewmodel.HomeStore
import java.util.Locale

object HomeScreen : Screen {
    private fun readResolve(): Any = HomeScreen

    @Composable
    override fun Content() {
        HomeScreen()
    }

    @Composable
    private fun HomeScreen(
        viewModel: HomeScreenModel = getScreenModel()
    ) {
        val navigator = LocalNavigator.currentOrThrow
        val state by viewModel.state.collectAsStateWithLifecycle()

//        val systemUiController = rememberSystemUiController()
//        SideEffect {
//            systemUiController.setStatusBarColor(color = Color.White)
//        }

        if (state.isLoading)
            LoadingView()
        else if (state.isError)
            ErrorView { viewModel.loadFilmsTop() }
        else
            HomeView(
                state = state,
                navigator,
                viewModel
            )
    }

    @Composable
    private fun HomeView(
        state: HomeStore.State,
        navigator: Navigator,
        viewModel: HomeScreenModel
    ) {
        BaseSurface {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                HomeAppBar(
                    state,
                    viewModel::onQuerySearchChange,
                    viewModel::onSearchClick,
                    viewModel::enableSearch
                )
                //жижа получилась
                if (state.isNotFound) {
                    NotFoundView {

                    }
                }
                if (!state.isNotFound)
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentPadding = PaddingValues(
                            horizontal = AppTheme.padding.base
                        ),
                        verticalArrangement = Arrangement.spacedBy(AppTheme.padding.base),
                    ) {
                        items(state.topFilms, key = { it.id }) {
                            FilmItem(
                                item = it,
                                navigator = navigator,
                                viewModel::onAddToFavoriteClick
                            )
                        }
                    }
            }
            if (!state.isNotFound)
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.padding.base),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    if (!state.isFavoriteType) {
                        SecondaryTextButton(
                            modifier = Modifier
                                .height(56.dp)
                                .weight(0.5f),
                            text = "Популярное"
                        ) { viewModel.setPageType(false) }
                        PrimaryTextButton(
                            modifier = Modifier
                                .height(56.dp)
                                .weight(0.5f),
                            text = "Избранное"
                        ) { viewModel.setPageType(true) }
                    } else {
                        PrimaryTextButton(
                            modifier = Modifier
                                .height(56.dp)
                                .weight(0.5f),
                            text = "Популярное"
                        ) { viewModel.setPageType(false) }
                        SecondaryTextButton(
                            modifier = Modifier
                                .height(56.dp)
                                .weight(0.5f),
                            text = "Избранное"
                        ) { viewModel.setPageType(true) }
                    }
                }
        }

    }

    @Composable
    private fun FilmItem(
        item: FilmSmall,
        navigator: Navigator,
        onAddToFavoriteClick: (Int) -> Unit
    ) {
        val detailScreen = rememberScreen(SharedScreen.Detail(id = item.id))
        val mContext = LocalContext.current

        CustomCard(
            onSmallClick = {
                navigator.push(detailScreen)
            },
            onLongClick = {
                onAddToFavoriteClick(item.id)
            },
        ) {
            Row(
                Modifier
                    .padding(AppTheme.padding.base)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.posterUrl)
                        .crossfade(true)
                        .dispatcher(Dispatchers.IO)
                        .build(),
                    placeholder = painterResource(id = R.drawable.placeholder),
                    contentDescription = item.nameRu,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(56.dp)
                        .clip(AppTheme.cornerShape.small)
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = AppTheme.padding.base),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = item.nameRu ?: item.nameEn ?: "",
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            style = AppTheme.typography.bodyBold
                        )
                        if (item.isFavorite)
                            Icon(
                                painter = painterResource(id = ru.glebik.feature.home.internal.R.drawable.ic_star),
                                contentDescription = "Favorite",
                                modifier = Modifier.size(18.dp),
                                tint = Color.Unspecified
                            )
                    }
                    Text(
                        text = "${
                            item.genres?.first() ?: "".replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            }
                        } (${item.year})",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = AppTheme.typography.hint
                    )
                }
            }
        }
    }
}

