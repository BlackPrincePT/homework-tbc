package com.perullheim.homework.presentation.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.perullheim.homework.presentation.screen.home.components.UserItem
import com.perullheim.homework.presentation.util.LaunchLifecycleEffect

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeContent(state = viewModel.uiState, onEvent = viewModel::onEvent)
}

@Composable
private fun HomeContent(
    state: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit
) {
    val listState = rememberLazyListState()

    LaunchLifecycleEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                val totalItems = layoutInfo.totalItemsCount
                val visibleItems = layoutInfo.visibleItemsInfo
                if (visibleItems.isNotEmpty()) {
                    val lastVisibleItemIndex = visibleItems.last().index
                    if (lastVisibleItemIndex >= totalItems - 1) {
                        onEvent(HomeUiEvent.LoadMoreUsers)
                    }
                }
            }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(state.users) { user ->
            UserItem(user)
        }
    }
}