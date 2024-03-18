package component.left_menu.table.row

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
            modifier = Modifier.width(35.dp).height(35.dp)
        ) {
            Text(
                text = text,
                modifier = Modifier.fillMaxSize()
                //fontSize = 70.sp
            )
        }

}