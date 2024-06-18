package component.left_menu.table.row

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*

 */
@Composable
@Preview
fun RowButtonAdd(
    id: Int,
    measurementData: MutableMap<Int, Pair<Double, Double>>,
    newMeasurementData: MutableMap<Int, Pair<Double, Double>>
) {
    var text by remember { mutableStateOf("+") }

    val firstOld = measurementData[id]?.first
    val firstNew = newMeasurementData[id]?.first

    val secondOld = measurementData[id]?.second
    val secondNew = newMeasurementData[id]?.second

    Box(
        modifier = Modifier.fillMaxHeight(),
        //.border(width = 1.dp, color = Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Button(
            enabled = if (firstOld == firstNew && secondOld == secondNew) false else true,
            onClick = {
                text = "+"
                val xy = newMeasurementData[id]
                if (xy != null && xy.first != null && xy.second != null) {
                    measurementData[id] = xy
                }
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
                    .wrapContentHeight(align = Alignment.CenterVertically),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
