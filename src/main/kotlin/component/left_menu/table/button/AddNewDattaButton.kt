package component.left_menu.table.button

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
@Preview
fun AddNewDataButton(
    leftAddDataIsActive : MutableState<Boolean>,
    measurementData: MutableMap<Int, Pair<Double, Double>>,
    newMeasurementData: MutableMap<Int, Pair<Double, Double>>
) {

    var text by remember { mutableStateOf("Добавить данные") }

    var enabledAddData by remember { mutableStateOf(true) }

    var enabledAddLine by remember { mutableStateOf(true) }

    var enabledCloseLine by remember { mutableStateOf(true) }
    /*
    LaunchedEffect(enabled) {
        if (enabled) return@LaunchedEffect
        else delay(1000L)
        enabled = true
        text = "Добавить данные"
        leftAddDataIsActive.value = false
    }
    */
    Button(
        onClick = {
            enabledAddData = true
            text = "Данные добавлены"
            //leftAddDataIsActive.value = true
            val lastKey = measurementData.keys.last()
            measurementData[lastKey +1] = Pair(0.0, 0.0)
        },
        enabled = enabledAddData
    ) {
        Text(text = text)
    }

    Button(
        onClick = {
            enabledAddLine = false
            leftAddDataIsActive.value = true
            enabledCloseLine = true
        },
        enabled = enabledAddLine
    ) {
        Text(text = "Добавить линию")
    }

    Button(
        onClick = {
            enabledCloseLine = false
            enabledAddLine = true
            leftAddDataIsActive.value = false
        },
        enabled = enabledCloseLine
    ) {
        Text(text ="Закрыть меню")
    }

}