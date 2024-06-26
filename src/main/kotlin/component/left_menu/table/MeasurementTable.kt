package component.left_menu

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import component.left_menu.table.TableRow


@Composable
@Preview
fun LeftMenuTable(
    modifier: Modifier = Modifier.fillMaxWidth(),
    measurementData: MutableMap<Int, Pair<Double, Double>>,
    newMeasurementData: MutableMap<Int, Pair<Double, Double>>
) {
    Column(modifier = modifier) {
        measurementData.forEach { (key, value) ->
            TableRow( id = key, measurementData = measurementData, newMeasurementData = newMeasurementData)
        }
    }
}
