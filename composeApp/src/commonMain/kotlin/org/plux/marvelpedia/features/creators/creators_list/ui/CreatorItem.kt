package org.plux.marvelpedia.features.creators.creators_list.ui

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
import org.plux.marvelpedia.features.creators.creators_list.model.Creator
import org.plux.marvelpedia.theme.Typography
import org.plux.marvelpedia.theme.lightPrimaryColor

@Composable
fun CreatorItem(
    creator: Creator,
    onClick: (Creator) -> Unit,
) {
    Card(
        backgroundColor = lightPrimaryColor,
        modifier = Modifier
            .fillMaxWidth()
            .size(180.dp)
            .padding(5.dp)
            .clickable { onClick.invoke(creator) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //Photo
            AsyncImage(
                model = creator.image,
                contentDescription = creator.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(100.dp)
            )

            //Name
            Text(
                text = creator.title,
                textAlign = TextAlign.Center,
                style = Typography.body1,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}