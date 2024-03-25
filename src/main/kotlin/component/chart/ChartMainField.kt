package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import component.chart.MainChart
import component.chart.Value
import enum.ColorEnum
import kotlin.math.roundToInt

@Composable
@Preview
fun ChartMainField(
    modifier: Modifier = Modifier.background(ColorEnum.WHITE.color),
    zoomWidth: Float, zoomHeight: Float, xZoom: Float, yZoom: Float,
    measurementData: Map<Int, Pair<Double, Double>>
) {
    var valueMap: MutableMap<Int, Value> = mutableMapOf()

    measurementData.forEach { k, v ->
        valueMap.put(k, Value(v.first, v.second))
    }
    Column(
        modifier = modifier
            .border(2.dp, Color.Green),
    ) {
        Row(
            modifier = Modifier.height(50.dp)
                .border(1.dp, Color.Blue)
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Top")
        }
        Row(
            modifier = Modifier
                .border(2.dp, Color.Red)
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .width(50.dp)
                    .border(2.dp, Color.Yellow)
                    .fillMaxHeight()
            ) {
                Text(text = "Left")
            }
            MainChart(
                valueMap = valueMap,
                modifier = Modifier
                    .weight(3f)
                    .border(2.dp, Color.Red)
                    .fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .width(50.dp)
                    .border(2.dp, Color.Yellow)
                    .fillMaxHeight()
            ) {
                Text(text = "Right")
            }
        }
        Row(
            modifier = Modifier
                .border(1.dp, Color.Blue)
                .fillMaxWidth()
                .height(50.dp)
        ) { Text(text = "Bottom") }
    }
}
