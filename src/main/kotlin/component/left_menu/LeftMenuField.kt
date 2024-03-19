package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import component.left_menu.LeftMenuTable
import enum.ColorEnum

@Composable
@Preview
fun LeftMenuField(
    modifier: Modifier = Modifier.background(ColorEnum.LIGHT_BLUE.color).fillMaxHeight(),
    measurementData:MutableMap<Int, Pair<Double, Double>>,
    changeCount: Int
) {

    Column(modifier = modifier) {
        Text(text = "LeftMenuField")
        LeftMenuTable(
            measurementData = measurementData,
            changeCount = changeCount
        )
    }
}