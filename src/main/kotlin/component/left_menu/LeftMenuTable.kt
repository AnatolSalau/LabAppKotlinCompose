package component.left_menu

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


@Composable
@Preview
fun LeftMenuTable(
    measurementData: Map<Int, Pair<Double, Double>>
/*    dataMeasure: HashMap<Int, Pair<Double, Double>>*/
) {
    Text(text = "Left menu table")
    Text(text = "Some value : ${measurementData}")
}
