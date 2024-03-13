package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import component.zoom.ZoomBorder
import enum.ColorEnum


@Composable
@Preview
fun ChartMainField(
    modifier: Modifier = Modifier.background(ColorEnum.WHITE.color),
    zoomWidth: Float, zoomHeight: Float, xZoom: Float, yZoom: Float
) {
    val localDensity = LocalDensity.current
    Box(modifier = modifier
        .width(200.dp)
        .border(width = 3.dp, color = Color.Red),
        contentAlignment = Alignment.Center,

    ) {
        Column {
            Text(text = "ChartMainField component")
        }
    }
}