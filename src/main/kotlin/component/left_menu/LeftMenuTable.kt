package component.left_menu

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
@Preview
fun LeftMenuTable(
    measurementData: Map<Int, Pair<Double, Double>>
/*    dataMeasure: HashMap<Int, Pair<Double, Double>>*/
) {
    val message = remember{mutableStateOf("")}

    Text(text = "Left menu table")
    Text(text = "Some value : ${measurementData}")
    Text("Text: ${message.value}")
    OutlinedTextField(
        modifier = Modifier.width(30.dp),
        value = message.value,
        textStyle = TextStyle(),
        onValueChange = {newText -> message.value = newText}
    )
}
