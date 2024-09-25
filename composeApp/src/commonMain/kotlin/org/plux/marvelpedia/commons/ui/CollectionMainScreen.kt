package org.plux.marvelpedia.commons.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import marvelpedia.composeapp.generated.resources.Res
import marvelpedia.composeapp.generated.resources.back_arrow
import marvelpedia.composeapp.generated.resources.search_icon
import org.jetbrains.compose.resources.painterResource
import org.plux.marvelpedia.commons.model.LazyListLaunchedEffect
import org.plux.marvelpedia.theme.Typography
import org.plux.marvelpedia.theme.primaryColor

@Composable
fun CollectionMainContent(
    title: String = "",
    content: LazyGridScope.() -> Unit,
    isLoading: Boolean = false,
    isFetching: Boolean = false,
    fetchDetected: () -> Unit = {},
    onSearchPressed: () -> Unit = {},
    onBackPressed: () -> Unit = {},
    topBar: @Composable () -> Unit = {
        CollectionMainScreenTopBar(
            title = title,
            onSearchPressed = { onSearchPressed.invoke() },
            onBackPressed = { onBackPressed.invoke() }
        )
    }
) {
    val showSearchBar = remember { mutableStateOf(true) }
    val endOfListReached = remember { mutableStateOf(true) }

    LaunchedEffect(isFetching) {
        if (!isFetching) endOfListReached.value = false
    }

    Scaffold(
        backgroundColor = primaryColor,
        topBar = {
            if (showSearchBar.value && !isLoading)
                topBar()
        },
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(color = primaryColor)
                .padding(innerPadding)
        ) {
            if (isLoading) {
                LoadingComponent()
            } else {
                Box {
                    CollectionMainList(
                        content = content,
                        onScrollUp = {
                            showSearchBar.value = true
                        },
                        onScrollDown = {
                            showSearchBar.value = false
                        },
                        onFetchDetected = {
                            if (!isFetching) fetchDetected.invoke()
                        },
                        onEndReached = {
                            endOfListReached.value = true
                        }
                    )

                    if (endOfListReached.value)
                        LoadingComponent(
                            modifier = Modifier.align(alignment = Alignment.BottomCenter)
                        )
                }
            }
        }
    }
}

@Composable
fun CollectionMainList(
    content: LazyGridScope.() -> Unit,
    onScrollUp: () -> Unit = {},
    onScrollDown: () -> Unit = {},
    onFetchDetected: () -> Unit = {},
    onEndReached: () -> Unit = {},
    isFetchable: Boolean = true
) {
    val listState = rememberLazyGridState()

    if (isFetchable)
        LazyListLaunchedEffect(
            listState = listState,
            buffer = 10,
            getScrollableEvents = true,
            onForwardScrollDetected = { onScrollDown.invoke() },
            onBackwardScrollDetected = { onScrollUp.invoke() },
            onFetchDetected = { onFetchDetected.invoke() },
            onEndReached = { onEndReached.invoke() }
        )



    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        contentPadding = PaddingValues(10.dp),
        state = listState,
        modifier = Modifier
            .background(color = primaryColor)
            .fillMaxSize(),
        content = content
    )
}

@Composable
fun CollectionMainScreenTopBar(
    title: String = "",
    onSearchPressed: () -> Unit,
    onBackPressed: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {

        Image(
            painter = painterResource(Res.drawable.back_arrow),
            contentDescription = "search",
            modifier = Modifier
                .clickable { onBackPressed.invoke() }
        )

        Text(
            text = title,
            style = Typography.subtitle1
        )

        Image(
            painter = painterResource(Res.drawable.search_icon),
            contentDescription = "search",
            modifier = Modifier
                .clickable { onSearchPressed.invoke() }
        )
    }
}