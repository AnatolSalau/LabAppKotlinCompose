package component.left_menu.table.button

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import enum.DragTypeEnum
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
@Preview
fun AddNewDataButton(
    leftAddDataIsActive : MutableState<Boolean>,
    measurementData: MutableMap<Int, Pair<Double, Double>>,
    newMeasurementData: MutableMap<Int, Pair<Double, Double>>
) {

    var text by remember { mutableStateOf("Добавить данные") }

    var enabled by remember { mutableStateOf(true) }

    LaunchedEffect(enabled) {
        if (enabled) return@LaunchedEffect
        else delay(1000L)
        enabled = true
        text = "Добавить данные"
        leftAddDataIsActive.value = false
    }

    Button(
        onClick = {
            enabled = false
            text = "Данные добавлены"
            leftAddDataIsActive.value = true
            val lastKey = measurementData.keys.last()
            measurementData[lastKey +1] = Pair(0.0, 0.0)
        },
        enabled = enabled
    ) {
        Text(text = text)
    }

}