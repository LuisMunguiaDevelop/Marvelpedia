package org.plux.marvelpedia.commons.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import marvelpedia.composeapp.generated.resources.Res
import marvelpedia.composeapp.generated.resources.back_arrow
import marvelpedia.composeapp.generated.resources.close_icon
import marvelpedia.composeapp.generated.resources.search_icon
import org.jetbrains.compose.resources.painterResource
import org.plux.marvelpedia.theme.Typography

@Composable
fun SearchBarComponent(
    placeHolderString: String = "",
    onTyped: (String) -> Unit,
    isLoading: Boolean = false,
    onBackPressed: () -> Unit = {},
    initialText: String = "",
) {

    var text by remember { mutableStateOf(initialText) }

    var lastTypingTime by remember { mutableStateOf(0L) }
    var typedTimes by remember { mutableStateOf(0) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    // Start a coroutine to check for timeout
    LaunchedEffect(key1 = text) {
        while (true) {
            if (Clock.System.now().toEpochMilliseconds() - lastTypingTime > 1000 && typedTimes > 0) {
                onTyped.invoke(text)
                break
            }
            delay(100)
        }
    }

    LaunchedEffect(key1 = Unit) { // Request focus when composable is initialized
        focusRequester.requestFocus()
        keyboardController?.show()

    }

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

        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
                lastTypingTime = Clock.System.now().toEpochMilliseconds()
                typedTimes++
            },
            maxLines = 1,
            placeholder = {
                Text(
                    text = placeHolderString,
                    style = Typography.subtitle2,
                )
            },
            textStyle = Typography.body1,
            shape = RoundedCornerShape(30.dp),
            leadingIcon = {
                Image(
                    painter = painterResource(Res.drawable.search_icon),
                    contentDescription = "search icon",
                )
            },
            trailingIcon = {

                if (isLoading) {
                    LoadingComponent(
                        modifier = Modifier.padding(5.dp).size(25.dp)
                    )
                } else if (text.isNotBlank()) {
                    Image(
                        painter = painterResource(Res.drawable.close_icon),
                        contentDescription = "close filtering",
                        modifier = Modifier
                            .clickable {
                                text = ""
                                onTyped.invoke(text)
                            }
                    )
                }

            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.statusBars)
                .focusRequester(focusRequester),
        )
    }
}