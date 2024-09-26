package org.plux.marvelpedia.commons.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.plux.marvelpedia.theme.primaryColor

@Composable
fun CollectionSearchScreen(
    onTypedDetected: (String) -> Unit,
    isLoading: Boolean,
    onBackPressed: () -> Unit = {},
    initialText: String = "",
    placerHolderString: String = "",
    gridContent: LazyGridScope.() -> Unit
){

    Scaffold(
        backgroundColor = primaryColor,
        topBar = {
            SearchBarComponent(
                placeHolderString = placerHolderString,
                onTyped = {
                    onTypedDetected.invoke(it)
                },
                isLoading = isLoading,
                onBackPressed = onBackPressed,
                initialText = initialText
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(10.dp),
                modifier = Modifier
                    .background(color = primaryColor)
                    .fillMaxSize(),
                content = gridContent
            )
        }
    }
}