package enum

import androidx.compose.ui.graphics.Color

/**
 * enum palette:
 * https://htmlcolorcodes.com/enum-chart/material-design-enum-chart/
 */
enum class ColorEnum(val color: Color, val txtName: String) {
    LIGHT_BLUE(Color(179, 229, 252), "LIGHT_BLUE"),
    CYAN(Color(178, 235, 242), "CYAN"),
    WHITE(Color(247, 249, 249 ), "WHITE"),
    BLUE_GREEN(Color(8, 143, 143 ), "BLUE_GREEN")
    ;
}