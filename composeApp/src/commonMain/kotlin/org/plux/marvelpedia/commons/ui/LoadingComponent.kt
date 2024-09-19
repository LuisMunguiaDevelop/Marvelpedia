package org.plux.marvelpedia.commons.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingComponent(
    modifier: Modifier = Modifier.size(50.dp)
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = MaterialTheme.colors.secondary,
    )
}