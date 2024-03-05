package component.zoom

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import enum.DragTypeEnum
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
@Preview
fun ZoomButton(xDragEnd: Float, yDragEnd: Float,
               yDragStart: Float, zoomWidth: Float,
               zoomHeight: Float, minZoomSize: Float,
               dragType: DragTypeEnum) {
    if (xDragEnd != 0f && yDragStart != 0f && dragType == DragTypeEnum.ON_DRAG_END
        && zoomWidth.absoluteValue > minZoomSize
        && zoomHeight.absoluteValue > minZoomSize
    ) {
        var text by remember { mutableStateOf("Увеличить") }
        Button(
            onClick = {
                text = "Увеличен"
            },
            modifier = Modifier
                .offset { IntOffset(xDragEnd.roundToInt(), yDragEnd.roundToInt()) }
        ) {
            Text(text)
        }
    }
}