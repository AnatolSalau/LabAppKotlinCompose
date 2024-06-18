package component.chart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.NativeCanvas

import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import org.jetbrains.skia.*

@Composable
fun ChartField(
    valueMap:MutableMap<Int, Value>, modifier: Modifier,
    density: Density = LocalDensity.current,
    measurementData: Map<Int, Pair<Double, Double>>
) {
    BoxWithConstraints (modifier = modifier){
        var width by remember {
            mutableStateOf(0F)
        }
        var height by remember {
            mutableStateOf(0F)
        }

        var topLeftY by remember {
            mutableStateOf(0F)
        }
        var topLeftX by remember {
            mutableStateOf(0F)
        }
        var bottomRightX by remember {
            mutableStateOf(0F)
        }
        var bottomRightY by remember {
            mutableStateOf(0F)
        }

        var gapX by remember { mutableStateOf(0F) }
        var gapY by remember { mutableStateOf(0F) }

        Box(Modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                run {
                    width = with(density) { coordinates.size.width.toFloat() }
                    height = with(density) { coordinates.size.height.toFloat() }
                    val rect = coordinates.boundsInRoot()
                    topLeftX = rect.topLeft.x
                    topLeftY = rect.topLeft.y

                    bottomRightX = rect.bottomRight.x
                    bottomRightY = rect.bottomRight.y

                    gapX = width / valueMap.size
                    gapY = height / valueMap.size
                }
            }
            .drawBehind {
                drawIntoCanvas {
                    drawLeftZeroCircle(it.nativeCanvas,"SOME TEXT : $topLeftY", 0F, height)
                    drawRightMaxCircle(it.nativeCanvas,"SOME TEXT : $topLeftY", width, 0F)
                    drawMiddleCircle(it.nativeCanvas,"SOME TEXT : $topLeftY", width/2 + 300, height/2 + 300)

                        drawLineThroughThreePoint(it.nativeCanvas,

                        0F, 0F, width/2 + 300, height/2 + 300, width + 1F, 0F)

                }
            }
        )
        /*

         */
        Column (Modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                run {
                    width = with(density) { coordinates.size.width.toFloat() }
                    height = with(density) { coordinates.size.height.toFloat() }
                    val rect = coordinates.boundsInRoot()
                    topLeftX = rect.topLeft.x
                    topLeftY = rect.topLeft.y

                    bottomRightX = rect.bottomRight.x
                    bottomRightY = rect.bottomRight.y

                }

            }) {
            Text(text = " Width :  ${width} ")
            Text(text = " Height :  ${height} ")
            Text(text = " topLeftX :  ${topLeftX} ")
            Text(text = " topLeftX :  ${topLeftY} ")
            Text(text = " bottomRightX :  ${bottomRightX} ")
            Text(text = " bottomRightX :  ${bottomRightY} ")
        }
    }

}

fun drawTextLine(canvas: NativeCanvas, text: String, x: Float, y: Float) {
    canvas.drawTextLine(TextLine.Companion.make(
        "SOME TEXT : $text", Font(Typeface.makeDefault())),
        x,
        y,
        Paint()
    )
}

fun drawLeftZeroCircle(canvas: NativeCanvas, text: String, x: Float, y: Float) {

    canvas.drawCircle(x,y, radius = 25f,  Paint().setARGB(255,220, 20, 60))
}

fun drawRightMaxCircle(canvas: NativeCanvas, text: String, x: Float, y: Float) {

    canvas.drawCircle(x,y, radius = 25f,  Paint().setARGB(255,220, 20, 60))
}

fun drawMiddleCircle(canvas: NativeCanvas, text: String, x: Float, y: Float) {
    canvas.drawTextLine(TextLine.Companion.make(
        "Middle circle : $text", Font(Typeface.makeDefault())),
        x,
        y,
        Paint()
    )
    canvas.drawCircle(x,y, radius = 25f,  Paint().setARGB(255,220, 20, 60))
}

fun drawLineThroughThreePoint(
    canvas: NativeCanvas, x1: Float, y1: Float,
    x2: Float, y2: Float,
    x3: Float, y3: Float
) {
    var paint = Paint()
    paint.apply {
        isAntiAlias = true
        color = Color.BLACK
        strokeWidth = 10F }
    canvas.drawLine(0F, 0F , 255F, 255F,  paint)
}


