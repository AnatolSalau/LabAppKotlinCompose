package component.chart

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

@Composable
fun MainChart(valueMap:MutableMap<Int, Value>, modifier: Modifier, density: Density = LocalDensity.current) {
    BoxWithConstraints (modifier = modifier){
        var width by remember {
            mutableStateOf(0F)
        }
        var height by remember {
            mutableStateOf(0F)
        }

        var topLeftX by remember {
            mutableStateOf(0F)
        }
        var bottomRightX by remember {
            mutableStateOf(0F)
        }


        Column (Modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                run {
                    width = with(density) { coordinates.size.width.toFloat() }
                    height = with(density) { coordinates.size.height.toFloat() }
                    val rect = coordinates.boundsInRoot()
                    topLeftX = rect.topLeft.x
                    bottomRightX = rect.bottomRight.x

                }

            }) {
            Text(text = " Width :  ${width} ")
            Text(text = " Height :  ${height} ")
            Text(text = " topLeftX :  ${topLeftX} ")
            Text(text = " bottomRightX :  ${bottomRightX} ")
            valueMap.forEach { (key, value) ->
                run {
                    Text(text = " ${key} : ( ${value.x} ,${value.y} )")
                }
            }
        }

    }

}