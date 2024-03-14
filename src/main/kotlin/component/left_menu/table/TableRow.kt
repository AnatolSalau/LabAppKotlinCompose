package component.left_menu.table

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TableRow() {
    val message = remember { mutableStateOf("") }

    Row(horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.padding(start = 2.dp)) {
        Text(text = "id")
        BasicTextField(
            value = message.value,
            singleLine = true,
            modifier = Modifier
                .wrapContentSize()
                .border(border = BorderStroke(1.dp, Color.White)),
            textStyle = TextStyle(textAlign = TextAlign.Center),
            onValueChange = { newText -> message.value = newText }
        )
        BasicTextField(
            value = message.value,
            singleLine = true,
            modifier = Modifier
                .wrapContentSize()
                .border(border = BorderStroke(1.dp, Color.White)),
            textStyle = TextStyle(textAlign = TextAlign.Center),
            onValueChange = { newText -> message.value = newText }
        )
    }
}