import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import enum.ColorEnum
import component.MainWindow

@Composable
@Preview
fun App() {
    MaterialTheme {
        MainWindow(
            modifier = Modifier
                .fillMaxSize()
        );
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(width = 800.dp, height = 600.dp),
        title = "Lab application",
        icon = painterResource("erythrocytes.png")
    ) {
        App()
    }
}
