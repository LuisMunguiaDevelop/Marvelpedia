package org.plux.marvelpedia.commons.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import marvelpedia.composeapp.generated.resources.Res
import marvelpedia.composeapp.generated.resources.back_arrow
import org.jetbrains.compose.resources.painterResource
import org.plux.marvelpedia.theme.Typography

@Composable
fun TopBarComponent(
    title: String = "",
    onBackPressed: () -> Unit = {}
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {

        Image(
            painter = painterResource(Res.drawable.back_arrow),
            contentDescription = "back button",
            modifier = Modifier
                .clickable { onBackPressed.invoke() }
        )


        //Title
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = Typography.body1.copy(fontSize = 30.sp),
            modifier = Modifier
                .padding(top = 5.dp)
                .weight(1f)
        )
    }
}