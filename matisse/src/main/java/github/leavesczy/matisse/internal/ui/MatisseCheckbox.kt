package github.leavesczy.matisse.internal.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import github.leavesczy.matisse.R

/**
 * @Author: leavesCZY
 * @Date: 2022/5/31 14:27
 * @Desc:
 */
@Composable
internal fun MatisseCheckbox(
    modifier: Modifier,
    text: String,
    enabled: Boolean,
    checked: Boolean,
    onClick: () -> Unit
) {
    val circleColor = colorResource(
        id = if (enabled) {
            R.color.matisse_check_box_circle_color
        } else {
            R.color.matisse_check_box_circle_color_if_disable
        }
    )
    val fillColor = colorResource(id = R.color.matisse_check_box_fill_color)
    val textColor = colorResource(id = R.color.matisse_check_box_text_color)
    val checkboxSize = 24.dp
    val textMeasurer = rememberTextMeasurer()
    Canvas(
        modifier = modifier
            .selectable(
                selected = checked,
                onClick = onClick,
                enabled = true,
                role = Role.Checkbox,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = checkboxSize / 2
                )
            )
            .requiredSize(size = checkboxSize)
    ) {
        val checkboxSide = this.size.width
        val checkboxRadius = checkboxSide / 2f
        val strokeWidth = checkboxSide / 10f
        drawCircle(
            color = circleColor,
            radius = checkboxRadius - strokeWidth / 2f,
            style = Stroke(width = strokeWidth)
        )
        if (checked) {
            drawCircle(
                color = fillColor,
                radius = checkboxRadius - strokeWidth
            )
        }
        if (text.isNotBlank()) {
            val textLayoutResult = textMeasurer.measure(
                text = text,
                style = TextStyle(
                    color = textColor,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            )
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    x = (checkboxSide - textLayoutResult.size.width) / 2,
                    y = (checkboxSide - textLayoutResult.size.height) / 2
                )
            )
        }
    }
}