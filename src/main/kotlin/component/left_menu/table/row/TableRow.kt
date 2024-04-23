package component.left_menu.table

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import component.left_menu.table.row.RowButton
import component.left_menu.table.row.TableCell
/*

 */
@Composable
fun TableRow(
    id: Int,
    measurementData: MutableMap<Int, Pair<Double, Double>>,
    newMeasurementData: MutableMap<Int, Pair<Double, Double>>,
) {
    Row(
        horizontalArrangement = Arrangement
            .spacedBy(2.dp)
        ,
        modifier = Modifier
            .padding(start = 2.dp)
            .height(30.dp)
            .border(width = 1.dp, color = Color.Black)
    ) {

        Text(modifier = Modifier
            .width(25.dp)
            .fillMaxHeight()
            //.border(width = 2.dp, color = Color.Red)
            .wrapContentHeight(align = Alignment.CenterVertically)
            , text = "${id.toString()})"
            , textAlign = TextAlign.Center
        )
        TableCell(id = id, measurementData = measurementData, newMeasurementData = newMeasurementData, isX = true)
        TableCell(id = id, measurementData = measurementData, newMeasurementData = newMeasurementData, isX = false)
        RowButton( id = id, measurementData = measurementData, newMeasurementData = newMeasurementData )
    }
    /*
        
     */
}