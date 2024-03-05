package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import component.zoom.ZoomBorder
import enum.ColorEnum


@Composable
@Preview
fun ChartMainField(modifier: Modifier = Modifier.background(ColorEnum.WHITE.color),
                   zoomWidth: Float, zoomHeight: Float, xZoom: Float, yZoom: Float
) {
    Box(modifier = modifier) {

    }
}