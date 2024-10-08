package org.plux.marvelpedia.features.series.series_list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.plux.marvelpedia.features.series.series_list.model.Serie
import org.plux.marvelpedia.theme.Typography
import org.plux.marvelpedia.theme.lightPrimaryColor

@Composable
fun SerieItem(
    serie: Serie,
    onClick: (Serie) -> Unit,
){
    Card(
        backgroundColor = lightPrimaryColor,
        modifier = Modifier
            .fillMaxWidth()
            .size(180.dp)
            .padding(5.dp)
            .clickable { onClick.invoke(serie) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //Photo
            AsyncImage(
                model = serie.image,
                contentDescription = serie.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(100.dp)
            )

            //Name
            Text(
                text = serie.title,
                textAlign = TextAlign.Center,
                style = Typography.body1,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}