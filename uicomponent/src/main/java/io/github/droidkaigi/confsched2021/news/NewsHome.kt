package io.github.droidkaigi.confsched2021.news

import androidx.compose.foundation.Text
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.material.BackdropValue
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalDrawerLayout
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import io.github.droidkaigi.confsched2021.news.article.ArticleScreen
import io.github.droidkaigi.confsched2021.news.ui.Conferenceapp2021newsTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalLazyDsl::class, ExperimentalMaterialApi::class)
@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    firstDrawerValue: DrawerValue = DrawerValue.Closed,
) {
    val drawerState = rememberDrawerState(firstDrawerValue)
    val navController = rememberNavController()
    ModalDrawerLayout(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = { DrawerContent(navController) }
    ) {
        NavHost(navController, startDestination = "articles") {
            composable("articles") {
                ArticleScreen(drawerState)
            }
            composable("about_this_app") { Text("ok?") }
        }
    }
}



@OptIn(ExperimentalCoroutinesApi::class)
@Preview(showBackground = true)
@Composable
fun NewsHomePreview() {
    Conferenceapp2021newsTheme {
        ProvideNewsViewModel(viewModel = object : INewsViewModel {
            override val filter: StateFlow<Filters> = MutableStateFlow(Filters())
            override val articles: StateFlow<Articles> = MutableStateFlow(Articles())
            override fun onFilterChanged(filters: Filters) {
            }

            override fun toggleFavorite(article: Article) {
            }
        }) {
            AppContent()
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@Preview(showBackground = true)
@Composable
fun DarkThemeNewsHomePreview() {
    Conferenceapp2021newsTheme(true) {
        ProvideNewsViewModel(viewModel = object : INewsViewModel {
            override val filter: StateFlow<Filters> = MutableStateFlow(Filters())
            override val articles: StateFlow<Articles> = MutableStateFlow(Articles())
            override fun onFilterChanged(filters: Filters) {
            }

            override fun toggleFavorite(article: Article) {
            }
        }) {
            AppContent()
        }
    }
}
