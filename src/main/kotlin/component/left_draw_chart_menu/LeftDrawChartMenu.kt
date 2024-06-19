package component.left_draw_chart_menu

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import component.left_menu.DrawChartTable
import enum.ColorEnum

@Composable
@Preview
fun LeftDrawChartMenu(
    modifier: Modifier = Modifier.background(ColorEnum.WHITE.color).fillMaxHeight(),
    chartValues:MutableMap<Int, Pair<Double, Double>>,
    chartValuesNew: MutableMap<Int, Pair<Double, Double>>
) {
    Column(modifier = modifier) {
        Button(
            onClick = {

            },
            enabled = true
        ) {
            Text(text = "Добавить линию")
        }
        DrawChartTable(chartValues = chartValues, chartValuesNew = chartValuesNew)
    }
}