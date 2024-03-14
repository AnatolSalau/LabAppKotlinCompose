package component.left_menu

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*


@OptIn(ExperimentalUnitApi::class)
@Composable
@Preview
fun LeftMenuTable(
    measurementData: Map<Int, Pair<Double, Double>>
/*    dataMeasure: HashMap<Int, Pair<Double, Double>>*/
) {
    val message = remember { mutableStateOf("") }

    Text(text = "Left menu table")
    Text(text = "Some value : ${measurementData}")
    Text("Text: ${message.value}")

    BasicTextField(
        value = message.value,
        singleLine = true,
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .wrapContentSize()
            .border(border = BorderStroke(1.dp, Color.White))
            .padding(20.dp),
        textStyle = TextStyle(textAlign = TextAlign.Center),
        onValueChange = { newText -> message.value = newText }
    )
}
