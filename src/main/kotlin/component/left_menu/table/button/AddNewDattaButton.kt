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

    Button(
        onClick = {
            enabledAddData = true
            text = "Добавить данные"
            //leftAddDataIsActive.value = true
            val lastKey = measurementData.keys.last()
            measurementData[lastKey +1] = Pair(0.0, 0.0)
        },
        enabled = enabledAddData
    ) {
        Text(text = text)
    }
    Text(text = " ")
    Button(
        onClick = {
        },
        enabled = true
    ) {
        Text(text = "Установить соединение")
    }

    Button(
        onClick = {
            enabledAddLine = false
            leftAddDataIsActive.value = true
            enabledCloseLine = true
        },
        enabled = enabledAddLine
    ) {
        Text(text = "Меню открыть")
    }

    Button(
        onClick = {
            enabledCloseLine = false
            enabledAddLine = true
            leftAddDataIsActive.value = false
        },
        enabled = enabledCloseLine
    ) {
        Text(text ="Меню закрыть")
    }
}