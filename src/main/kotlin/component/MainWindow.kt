package component

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput

import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import component.zoom.ZoomBorder
import component.zoom.Point
import component.zoom.ZoomButton
import enum.ColorEnum
import enum.DragTypeEnum
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.absoluteValue

const val MIN_ZOOM_SIZE: Float = 20f
const val CHART_LEFT_OFFSET = 200f



@Composable
@Preview
fun MainWindow(modifier: Modifier = Modifier.fillMaxSize().background(ColorEnum.WHITE.color)) {

    val coroutineScope = rememberCoroutineScope()
    val interactionSource = remember { MutableInteractionSource() }
    var CHANGE_COUNT by remember { mutableStateOf(0) }

    val measurementData: MutableMap<Int, Pair<Double, Double>> = remember {
        mutableStateMapOf(
            1 to Pair(1.0, 2.0),
            2 to Pair(3.0, 4.0),
            3 to Pair(5.0, 6.0),
            4 to Pair(7.0, 8.0),
            5 to Pair(9.0, 10.0),
            6 to Pair(11.0, 12.0),
            7 to Pair(13.0, 14.0),
            8 to Pair(15.0, 16.0),
            9 to Pair(17.0, 18.0),
            10 to Pair(19.0, 20.0),
            11 to Pair(21.0, 22.0),
            12 to Pair(23.0, 24.0),
            13 to Pair(25.0, 26.0),
            14 to Pair(27.0, 28.0),
            15 to Pair(29.0, 30.0),
            16 to Pair(31.0, 32.0),
            17 to Pair(33.0, 34.0),
            18 to Pair(35.0, 36.0),
            19 to Pair(37.0, 38.0),
        )
    }
    /*

     */

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
    val zoomColor by remember { mutableStateOf(ColorEnum.BLUE_GREEN) }

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
                .offset { IntOffset(0, 900) }.zIndex(1f),
            xTap = xTap, yTap = yTap,
            xDragStart = xDragStart, yDragStart = yDragStart,
            xDragEnd = xDragEnd, yDragEnd = yDragEnd,
            dragType = dragType,
            xZoom = xZoom, yZoom = yZoom,
            zoomWidth = zoomWidth, zoomHeight = zoomHeight,
            zoomColor = zoomColor,
            measurementData = measurementData
        )

        Box {
            LeftMenuField(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(CHART_LEFT_OFFSET.dp)
                    .background(color = ColorEnum.LIGHT_BLUE.color)
                    .zIndex(1f),
                measurementData = measurementData,
                changeCount = CHANGE_COUNT
            )

            /*
                ac
             */
            ChartMainField(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ColorEnum.PASTEL_BLUE.color)
                    .border(width = 1.dp, color = Color.Green)
                    .padding(start = CHART_LEFT_OFFSET.dp),

                xZoom = xZoom, yZoom = yZoom,
                zoomWidth = zoomWidth, zoomHeight = zoomHeight,
                measurementData = measurementData
            )
            ZoomBorder(zoomWidth = zoomWidth, zoomHeight = zoomHeight, xZoom = xZoom, yZoom =
            yZoom)

            Point(x = xTap, y = yTap, color = ColorEnum.RED) // tap point
            Point(x = xDragStart, y = yDragStart, color = ColorEnum.RED) // drag start point
            ZoomButton(
                xDragEnd = xDragEnd, yDragEnd = yDragEnd,
                yDragStart = yDragStart, zoomWidth = zoomWidth,
                zoomHeight = zoomHeight, minZoomSize = MIN_ZOOM_SIZE,
                dragType = dragType
            )
        }
    }
}
