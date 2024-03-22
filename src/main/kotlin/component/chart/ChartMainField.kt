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
import enum.ColorEnum
import kotlin.math.roundToInt

data class Point(val x: Float, val y: Float)

data class PixelPont(val valueX: Float, val valueY: Float, val pixelX: Float, val pixelY: Float)

val valuesLineChart = mutableListOf(
    Point(0f, 1f),
    Point(1.5f, 1.2f),
    Point(2f, 0.9f),
    Point(2.5f, 2f),
    Point(3f, 1.3f),
    Point(3.5f, 3.2f),
    Point(4f, 0.8f),
)
var pixelPointsLineChart: List<PixelPont>? = mutableStateListOf()

@Composable
@Preview
fun ChartMainField(
    modifier: Modifier = Modifier.background(ColorEnum.WHITE.color),
    zoomWidth: Float, zoomHeight: Float, xZoom: Float, yZoom: Float,
    measurementData: Map<Int, Pair<Double, Double>>
) {



    pixelPointsLineChart?.forEachIndexed { index, point ->
        //draw points
        Canvas(
            modifier = Modifier,
            onDraw = {
                drawCircle(
                    color = Color.Red,
                    radius = 20f,
                    center = Offset(point.pixelX, point.pixelY)
                )
            })
    }
    Column(
        modifier = modifier
            .border(2.dp, Color.Green),
        //verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.height(50.dp)
                .border(1.dp, Color.Blue)
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Top")
            pixelPointsLineChart?.forEachIndexed { index, point ->
                //draw points
                Text(text = "{PixelX : ${point.pixelX}, PixelY : ${point.pixelY}}")
                drawPoint(point.pixelX, point.pixelY)
            }
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
            Column(
                modifier = Modifier
                    .weight(1f)
                    .border(2.dp, Color.Yellow)
                    .fillMaxHeight()
            ) {
                Text(text = "Main chart")
                if (pixelPointsLineChart != null) {
                    drawLineChart(valuesLineChart = valuesLineChart)
                }
            }

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
/*    }*/
}

// simple extension function that allows conversion between ranges
fun Float.mapValueToDifferentRange(
    inMin: Float,
    inMax: Float,
    outMin: Float,
    outMax: Float
) = (this - inMin) * (outMax - outMin) / (inMax - inMin) + outMin

@Composable
fun drawLineChart(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .border(width = 2.dp, color = Color.Red),
    valuesLineChart: MutableList<Point>,

) {
    // find max and min value of X, we will need that later
    val minXValue = valuesLineChart.minOf { it.x }
    val maxXValue = valuesLineChart.maxOf { it.x }

    // find max and min value of Y, we will need that later
    val minYValue = valuesLineChart.minOf { it.y }
    val maxYValue = valuesLineChart.maxOf { it.y }

    Box(modifier = modifier
        .drawBehind
        { // we use drawBehind() method to create canvas

            // map data points to pixel values, in canvas we think in pixels
            pixelPointsLineChart = valuesLineChart.map {
                // we use extension function to convert and scale initial values to pixels
                val pixelX = it.x.mapValueToDifferentRange(
                    inMin = minXValue,
                    inMax = maxXValue,
                    outMin = 0f,
                    outMax = size.width
                )
                it.x
                // same with y axis
                val pixelY = it.y.mapValueToDifferentRange(
                    inMin = minYValue,
                    inMax = maxYValue,
                    outMin = size.height,
                    outMax = 0f
                )
                PixelPont(valueX = it.x, valueY = it.y, pixelX = pixelX, pixelY = pixelY)
            }
            val path = Path() // prepare path to draw

            // in the loop below we fill our path
            pixelPointsLineChart!!.forEachIndexed { index, point ->
                if (index == 0) { // for the first point we just move drawing cursor to the position
                    path.moveTo(point.pixelX, point.pixelY)
                    //drawPoint(point.pixelX, point.pixelY)
                } else {
                    path.lineTo(point.pixelX, point.pixelY) // for rest of points we draw the line
                }
                //draw points
                drawCircle(
                    color = Color.Blue,
                    radius = 10f,
                    center = Offset(point.pixelX, point.pixelY)
                )
            }
            // and finally we draw the path
            drawPath(
                path,
                color = Color.Blue,
                style = Stroke(width = 3f)
            )
        })
}


@Composable
fun drawPoint(pixelX: Float, pixelY: Float) {
    Canvas(
        modifier = Modifier
            .offset { IntOffset(pixelX.roundToInt(), pixelY.roundToInt()) }
    ) {
        drawCircle(
            color = Color.Red,
            radius = 20f,
        )
    }
}
