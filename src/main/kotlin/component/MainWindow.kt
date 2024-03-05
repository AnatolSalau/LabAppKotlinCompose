package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput

import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import enum.ColorEnum
import enum.DragTypeEnum
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.absoluteValue

const val MIN_ZOOM_SIZE: Float = 20f
const val LEFT_MENU_WIDTH: Float = 300f

@Composable
@Preview
fun MainWindow(modifier: Modifier = Modifier.fillMaxSize().background(ColorEnum.WHITE.color)) {

    val coroutineScope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }


    var xTap by remember { mutableStateOf(0f) }
    var yTap by remember { mutableStateOf(0f) }
    var xDragStart by remember { mutableStateOf(0f) }
    var yDragStart by remember { mutableStateOf(0f) }
    var xDragEnd by remember { mutableStateOf(0f) }
    var yDragEnd by remember { mutableStateOf(0f) }

    var xZoom by remember { mutableStateOf(0f) }
    var yZoom by remember { mutableStateOf(0f) }
    var zoomWidth by remember { mutableStateOf(0f) }
    var zoomHeight by remember { mutableStateOf(0f) }
    var dragType by remember { mutableStateOf(DragTypeEnum.ON_DRAG_CANCEL) }
    var zoomColor by remember { mutableStateOf(ColorEnum.BLUE_GREEN) }


    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            run {
                when (interaction) {
                    is DragInteraction.Start -> {

                        xDragStart = xTap
                        yDragStart = yTap
                    }
                    is DragInteraction.Stop -> {

                        xDragEnd = xTap
                        yDragEnd = yTap
                    }
                    is DragInteraction.Cancel -> {

                    }
                }
            }
        }
    }
    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures { offset: Offset ->
                    // Touch coordinates on image
                    xTap = offset.x
                    yTap = offset.y;

                    xZoom = 0f
                    yZoom = 0f
                    zoomWidth = 0f
                    zoomHeight = 0f

                    xDragStart = 0f
                    yDragStart = 0f
                    xDragEnd = 0f
                    yDragEnd = 0f

                }
            }
            .pointerInput(Unit) {
                var interaction: DragInteraction.Start? = null

                detectDragGestures(
                    onDragStart = { offset ->
                        run {
                            xTap = offset.x
                            yTap = offset.y

                            xDragStart = offset.x
                            yDragStart = offset.x
                            xDragEnd = 0f
                            yDragEnd = 0f

                            dragType = DragTypeEnum.ON_DRAG_START

                            //zoomButtonText = "Увеличить"

                        }
                        coroutineScope.launch {
                            interaction = DragInteraction.Start()
                            interaction?.run {
                                interactionSource.emit(this)
                            }

                        }
                    },
                    onDrag = { change: PointerInputChange, dragAmount: Offset ->
                        run {
                            xTap += dragAmount.x
                            yTap += dragAmount.y
                            xZoom = xTap
                            yZoom = yTap

                            dragType = DragTypeEnum.ON_DRAG

                            fun calculateCoordinates(
                                dragCoordinate: Float,
                                tapCoordinate: Float
                            ): Float {
                                val size = dragCoordinate - tapCoordinate

                                if (abs(dragCoordinate.absoluteValue - tapCoordinate.absoluteValue) <= MIN_ZOOM_SIZE) return 0f


                                return size
                            }

                            var width = calculateCoordinates(xDragStart, xTap)
                            var height = calculateCoordinates(yDragStart, yTap)

                            if (width.absoluteValue < MIN_ZOOM_SIZE || height.absoluteValue < MIN_ZOOM_SIZE) {
                                zoomWidth = 0f
                                zoomHeight = 0f
                            } else {
                                zoomWidth = width
                                zoomHeight = height
                            }

                        }
                    },
                    onDragCancel = {

                        xDragEnd = xTap
                        yDragEnd = xTap

                        dragType = DragTypeEnum.ON_DRAG_CANCEL

                        coroutineScope.launch {
                            interaction?.run {
                                interactionSource.emit(DragInteraction.Cancel(this))
                            }
                        }
                    },
                    onDragEnd = {
                        xDragEnd = xTap
                        yDragEnd = xTap

                        dragType = DragTypeEnum.ON_DRAG_END

                        coroutineScope.launch {
                            interaction?.run {
                                interactionSource.emit(DragInteraction.Stop(this))
                            }
                        }

                    }
                )
            }
    ) {
        StatisticField(
            modifier = Modifier
                .offset { IntOffset(0, 30) }.zIndex(1f),
            xTap = xTap, yTap = yTap,
            xDragStart = xDragStart, yDragStart = yDragStart,
            xDragEnd = xDragEnd, yDragEnd = yDragEnd,
            dragType = dragType,
            xZoom = xZoom, yZoom = yZoom,
            zoomWidth = zoomWidth, zoomHeight = zoomHeight,
            zoomColor = zoomColor
        )
        Row {
            LeftMenuField(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(LEFT_MENU_WIDTH.dp)
                    .background(color = ColorEnum.LIGHT_BLUE.color)
            )
            ChartMainField(
                modifier = Modifier.fillMaxSize().background(ColorEnum.WHITE.color),
                xZoom = xZoom - (LEFT_MENU_WIDTH + (LEFT_MENU_WIDTH/2) ), yZoom = yZoom,
                zoomWidth = zoomWidth, zoomHeight = zoomHeight
            )
        }
    }
}