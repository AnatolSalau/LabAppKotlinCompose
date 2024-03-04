package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput

import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import enum.ColorEnum
import enum.DragTypeEnum

@Composable
@Preview
fun MainWindow(modifier: Modifier = Modifier.fillMaxSize().background(ColorEnum.WHITE.color)) {

    var text by remember { mutableStateOf("Hello, World!") }

    var xTap by remember { mutableStateOf(0f) }
    var yTap by remember { mutableStateOf(0f) }
    var xDragStart by remember { mutableStateOf(0f) }
    var yDragStart by remember { mutableStateOf(0f) }
    var xDragEnd by remember { mutableStateOf(0f) }
    var yDragEnd by remember { mutableStateOf(0f) }

    var xZoom by remember { mutableStateOf(0f) }
    var yZoom by remember { mutableStateOf(0f) }
    var zoomWidth by remember { mutableStateOf(0f) }
    var zoomHeight by remember { mutableStateOf(0f) }

    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures { offset: Offset ->
                    // Touch coordinates on image
                    xTap = offset.x
                    yTap = offset.y;

                    xZoom = 0f
                    yZoom = 0f
                    zoomWidth = 0f
                    zoomHeight = 0f

                    xDragStart = 0f
                    yDragStart = 0f
                    xDragEnd = 0f
                    yDragEnd = 0f

                }
            }
    ) {
        Button(onClick = {
            text = "Hello, Desktop!"
        }) {
            Text(text)
        }

        StatisticField(
            modifier = Modifier
                .offset { IntOffset(0, 30) }.zIndex(1f),
            xTap = xTap, yTap = yTap,
            xDragStart = xDragStart, yDragStart = yDragStart,
            xDragEnd = xDragEnd, yDragEnd = yDragEnd,
            dragType = DragTypeEnum.ON_DRAG,
            xZoom = xZoom, yZoom = yZoom,
            zoomWidth = zoomWidth, zoomHeight = zoomWidth,
            zoomColor = ColorEnum.LIGHT_BLUE
        )

        ChartMainField(modifier = Modifier.fillMaxSize().background(ColorEnum.WHITE.color))


    }

}