package component.left_menu.table.row

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*

 */
@Composable
@Preview
fun RowButton(
    id: Int,
    measurementData: MutableMap<Int, Pair<Double, Double>>,
    updateIndex: Unit
) {
        var text by remember { mutableStateOf("+") }
    Box (
        modifier = Modifier.fillMaxHeight(),
            //.border(width = 1.dp, color = Color.Red),
        contentAlignment = Alignment.Center
            ) {
        Button(
            onClick = {
                text = "-"
                measurementData.remove(id)
                measurementData[id] = Pair( -99.0, -99.0)

                updateIndex;
            },
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .width(25.dp)
                .height(25.dp)

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
}
