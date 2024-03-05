package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import enum.ColorEnum

@Composable
@Preview
fun LeftMenuField(modifier: Modifier = Modifier.background(ColorEnum.LIGHT_BLUE.color).fillMaxHeight()) {
    Column(modifier = modifier) { Text(text = "LeftMenuField") }
}