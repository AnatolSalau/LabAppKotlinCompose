package component.chart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import org.jetbrains.skia.Font
import org.jetbrains.skia.Paint
import org.jetbrains.skia.TextLine
import org.jetbrains.skia.Typeface

@Composable
fun ChartField(
    valueMap:MutableMap<Int, Value>, modifier: Modifier,
    density: Density = LocalDensity.current) {
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
                }
            }
            .drawBehind {
                drawIntoCanvas {
                    it.nativeCanvas.drawTextLine(TextLine.Companion.make(
                        "SOME TEXT : $topLeftY", Font(Typeface.makeDefault())),
                        width,
                        height,
                        Paint()
                    )
                }
            }
        )

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