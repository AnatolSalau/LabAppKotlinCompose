package component.left_menu.table.row

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun RowButton() {
        var text by remember { mutableStateOf("+") }
        Button(
            onClick = {
                text = "-"
            },
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.width(35.dp).height(35.dp)
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .fillMaxSize()
                    //.border(width = 1.dp, color = Color.Green)
                    .wrapContentHeight(align = Alignment.CenterVertically)
                ,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }

}