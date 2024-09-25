package org.plux.marvelpedia.commons.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.plux.marvelpedia.theme.Typography

@Composable
fun SectionButton(
    buttonTitle: String,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        shape = RoundedCornerShape(20.dp)
    ){
        Text(
            text = buttonTitle,
            style = Typography.caption.copy(color = Color.White)
        )
    }
}