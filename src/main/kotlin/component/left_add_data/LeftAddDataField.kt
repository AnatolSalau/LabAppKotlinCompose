package component.left_add_data

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import enum.ColorEnum

@Composable
@Preview
fun LeftAddDataField(
    modifier: Modifier = Modifier.background(ColorEnum.WHITE.color).fillMaxHeight(),
    measurementData:MutableMap<Int, Pair<Double, Double>>,
    newMeasurementData:MutableMap<Int, Pair<Double, Double>>,

) {
    Column(modifier = modifier) {
        Text(text = "Add new measurements")
    }
}