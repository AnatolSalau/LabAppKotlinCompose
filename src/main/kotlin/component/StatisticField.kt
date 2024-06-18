package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import enum.ColorEnum
import enum.DragTypeEnum
import kotlin.math.absoluteValue

@Composable
@Preview
fun StatisticField(
    modifier: Modifier,
    xTap: Float, yTap: Float,
    xDragStart: Float, yDragStart: Float,
    xDragEnd: Float, yDragEnd: Float,
    dragType: DragTypeEnum,
    xZoom: Float, yZoom: Float,
    zoomWidth: Float, zoomHeight: Float,
    zoomColor: ColorEnum,
    measurementData: MutableMap<Int, Pair<Double, Double>>,
    newMeasurementData: MutableMap<Int, Pair<Double, Double>>
) {
    /*
         what
     */
    Row(modifier = modifier) {
        Column {
            Spacer(Modifier.height(10.dp))
            Text("Tap (X, Y) coordinates")
            Text(xTap.toString())
            Text(yTap.toString())

            Spacer(Modifier.height(10.dp))
            Text("Drag start (X, Y) coordinates")
            Text(xDragStart.toString())
            Text(yDragStart.toString())

            Spacer(Modifier.height(10.dp))
            Text("Drag end (X, Y) coordinates")
            Text(xDragEnd.toString())
            Text(yDragEnd.toString())

            Spacer(Modifier.height(10.dp))
            Text("Drag status : ${dragType.name}")

            Spacer(Modifier.height(10.dp))
            Text("Zoom width : ${zoomWidth.absoluteValue}")
            Text("Zoom height : ${zoomHeight.absoluteValue}")
            Text("Zoom width : ${xZoom.toInt()}")
            Text("Zoom height : ${yZoom.toInt()}")
            Text("Zoom color : ${zoomColor.txtName}")
        }
        Column {
            Text("Measurement data")
            measurementData.forEach { (key, value) ->
                run {
                    Text(text = " ${key} : ( ${value.first} ,${value.second} )")
                }
            }
        }
        /*

         */
        Column {
            Text("New measurement data")
            newMeasurementData.forEach { (key, value) ->
                run {
                    Text(text = " ${key} : ( ${value.first} ,${value.second} )")
                }
            }
        }
    }
}
