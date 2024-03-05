package component.chart.zoom

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.zIndex
import enum.ColorEnum

@Composable
@Preview
fun ZoomBorder(
    color: Color = ColorEnum.BLUE_GREEN.color,
    xZoom: Float, yZoom: Float,
    zoomWidth: Float, zoomHeight: Float,
) {
    Canvas(
        modifier = Modifier.zIndex(1f)
    ) {
        drawRoundRect(
            color = color,
            size = Size(
                width = zoomWidth,
                height = zoomHeight
            ),
            topLeft = Offset(
                xZoom,
                yZoom
            ),
            cornerRadius = CornerRadius(10f),
            style = Stroke(
                width = 4f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 20f), 5f),
            ),
        )
    }
}