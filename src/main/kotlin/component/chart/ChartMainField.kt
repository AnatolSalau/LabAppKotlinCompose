package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import component.chart.zoom.ZoomBorder
import enum.ColorEnum


@Composable
@Preview
fun ChartMainField(modifier: Modifier = Modifier.background(ColorEnum.WHITE.color),
                   zoomWidth: Float, zoomHeight: Float, xZoom: Float, yZoom: Float
) {
    Box(modifier = modifier) {
        ZoomBorder(zoomWidth = zoomWidth, zoomHeight = zoomHeight, xZoom = xZoom, yZoom = yZoom)
    }
}