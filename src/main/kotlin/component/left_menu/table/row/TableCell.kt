package component.left_menu.table.row

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TableCell(
    modifier: Modifier = Modifier
        .width(50.dp)
        .fillMaxHeight()
        .border(width = 2.dp, color = Color.Black)
    ,
    value: Double
) {
    var oldValue = remember { mutableStateOf(value.toString()) }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            //value = message.value,
            value = oldValue.value,
            singleLine = true,
            modifier = Modifier
                .wrapContentSize()
                //.border(border = BorderStroke(1.dp, Color.White))
            ,
            textStyle = TextStyle(textAlign = TextAlign.Center),
            onValueChange = { newText ->
                oldValue.value = newText
            }
        )
    }
}