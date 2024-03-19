package component.left_menu

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.left_menu.table.TableRow
import enum.ColorEnum


@Composable
@Preview
fun LeftMenuTable(
    modifier: Modifier = Modifier.fillMaxWidth(),
    measurementData: MutableMap<Int, Pair<Double, Double>>,
    changeCount: Int
) {
    Column(modifier = modifier) {
        measurementData.forEach { (key, value) ->
            TableRow( id = key, x = value.first , y = value.second, measurementData = measurementData, changeCount = changeCount)
        }
    }
}
