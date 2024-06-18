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
import androidx.compose.ui.zIndex
import org.jetbrains.skia.*
import kotlin.math.max
import kotlin.math.min

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

        var minX: Double by remember {
            mutableStateOf(0.0)
        }

        var minY: Double by remember {
            mutableStateOf(0.0)
        }

        var maxX: Double by remember {
            mutableStateOf(0.0)
        }

        var maxY: Double by remember {
            mutableStateOf(0.0)
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




                    measurementData.entries.forEach { entry ->
                        run {
                            val currX = entry.value.first
                            val currY = entry.value.second
                            minX = min(minX, currX)
                            minY = min(minY, currY)
                            maxX = max(maxX, currX)
                            maxY = max(maxY, currY)
                        }
                    }
                }
            }
            .drawBehind {
                drawIntoCanvas {
                    //drawLeftZeroCircle(it.nativeCanvas,"SOME TEXT : $topLeftY", 0F, height)
                    //drawRightMaxCircle(it.nativeCanvas,"SOME TEXT : $topLeftY", width, 0F)
                    //drawMiddleCircle(it.nativeCanvas,"SOME TEXT : $topLeftY", width/2 + 300, height/2 + 300)
                    //drawLine(it.nativeCanvas, 0F, 0F, width/2 + 300, height/2 + 300, width + 1F, 0F)

                    var measurementDataSortedFirstValue: Set<Map.Entry<Int, Pair<Double, Double>>> =
                        measurementData.toMap().entries.sortedBy { entry -> entry.value.first }.toSet()


                    var measurementDataSortedSecondValue: Set<Map.Entry<Int, Pair<Double, Double>>> =
                        measurementData.toMap().entries.sortedBy { entry -> entry.value.second }.toSet()

                    var allY: List<Double> = measurementDataSortedFirstValue
                        .map { entry -> entry.value.first  }
                        .toSet()
                        .toList()

                    var allX: List<Double> = measurementDataSortedSecondValue
                        .map { entry -> entry.value.second  }
                        .toSet()
                        .toList()

                    gapX = width / allX.size
                    gapY = height / allY.size

                    // draw Y
                    var currHeight = height - gapY
                    allY
                        .forEach { entry ->
                        run {
                            drawTextLine(it.nativeCanvas,"${entry}", 0F - 50, currHeight)
                            currHeight = currHeight - gapY
                        }
                    }

                    // draw X
                    var currWidth = 0F + gapX

                    allX
                        .forEach { entry ->
                            run {
                                drawTextLine(it.nativeCanvas,"${entry}", currWidth, height + 50)
                                currWidth = currWidth + gapX
                            }
                        }
                    //draw point
                    var allCurrPointY: ArrayList<Float> = ArrayList()
                    var allCurrPointX: ArrayList<Float> = ArrayList()

                    measurementData.entries.forEach { entry ->
                        run {
                            var currPointX = 0F
                            var currPointY = height.toFloat()
                            var i: Int = 0;
                            do {
                                currPointY = currPointY - gapY
                                allCurrPointY.add(currPointY)
                                i++
                            } while (entry.value.first != allY[i-1])
                            var j: Int = 0;
                            do {
                                currPointX = currPointX + gapX
                                allCurrPointX.add(currPointX)
                                j++
                            } while (entry.value.second != allX[j-1])

                            drawPoint(it.nativeCanvas,
                                "Y : ${entry.value.first}, X : ${entry.value.second}, currPointX = ${currPointX}, currPointY = ${currPointY}"
                                , currPointX, currPointY)
                        }
                    }
                    /*
                    var z1 = 0
                    var z2 = 1
                    do {
                        drawChartLine(
                            it.nativeCanvas, allCurrPointX[z1], allCurrPointY[z1], allCurrPointX[z2], allCurrPointY[z2]
                        )
                        allCurrPointX.removeAt(z1)
                        allCurrPointY.removeAt(z1)
                        z1++
                        z2++
                    } while (z2 < allCurrPointX.size)
                    */
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
            Text(text = " minX :  ${minX} ")
            Text(text = " minY :  ${minY} ")
            Text(text = " maxX :  ${maxX} ")
            Text(text = " maxY :  ${maxY} ")
            Text(text = " gapX :  ${gapX} ")
            Text(text = " gapY :  ${gapY} ")
        }
    }

}

fun drawTextLine(canvas: NativeCanvas, text: String, x: Float, y: Float) {
    canvas.drawTextLine(TextLine.Companion.make(
        text, Font(Typeface.makeDefault(), 30F)),
        x,
        y,
        Paint()
    )
}

fun drawPoint(canvas: NativeCanvas, text: String, x: Float, y: Float) {
    canvas.drawTextLine(TextLine.Companion.make(
        "$text", Font(Typeface.makeDefault(), 25F)),
        x,
        y,
        Paint()
    )
    canvas.drawCircle(x,y, radius = 10f,  Paint().setARGB(255,220, 20, 60))
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

fun drawChartLine(
    canvas: NativeCanvas, x1: Float, y1: Float,
    x2: Float, y2: Float,
) {
    val paint = Paint()
    paint.apply {
        isAntiAlias = true
        color = Color.BLACK
        strokeWidth = 5F }
    canvas.drawLine(x1, y1 , x2, y2,  paint)
}


