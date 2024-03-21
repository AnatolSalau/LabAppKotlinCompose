package component.left_menu.table.row

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

val REGEX = "-?[0-9]+(\\.[0-9]+)?".toRegex()
@Composable
fun TableCell(
    modifier: Modifier = Modifier
        .width(50.dp)
        .fillMaxHeight()
    //.border(width = 2.dp, color = Color.Black)
    ,
    id: Int,
    measurementData: MutableMap<Int, Pair<Double, Double>>,
    newMeasurementData: MutableMap<Int, Pair<Double, Double>>,
    isX: Boolean
) {
    var oldValue = remember {
        mutableStateOf(
            if (isX) measurementData[id]?.first.toString()
            else measurementData[id]?.second.toString()
        )
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = oldValue.value,
            singleLine = true,
            modifier = Modifier
                .wrapContentSize()
            .border(border = BorderStroke(1.dp, Color.White))
            ,
            textStyle = TextStyle(textAlign = TextAlign.Center),
            onValueChange = { newText: String ->
                oldValue.value = newText

                if(!newText.isBlank() && newText.matches(REGEX)) {
                    val value = newText.toDouble()
                    if (isX ) {
                        val second = measurementData[id]?.second
                        if (second != null) {
                            newMeasurementData[id] = Pair(value, second)
                        }
                    } else {
                        val first = measurementData[id]?.first
                        if (first != null) {
                            newMeasurementData[id] = Pair(first, value)
                        }
                    }
                }
            }
        )

    }
}

