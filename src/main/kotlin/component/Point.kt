package component.zoom

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import enum.ColorEnum

@Composable
@Preview
fun Point(x: Float, y: Float, color: ColorEnum) {
    Canvas(modifier = Modifier.size(5.dp), onDraw = {
        drawCircle(color = color.color, center = Offset(x, y) )
    })
}
