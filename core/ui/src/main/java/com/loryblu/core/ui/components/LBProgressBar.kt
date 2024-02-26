import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.theme.LBAfternoonBlue
import com.loryblu.core.ui.theme.LBCardSoftBlue

@Composable
fun LBProgressBar(modifier: Modifier = Modifier, currentStep: Int) {
    val totalSteps = 4

    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            for (step in 1..totalSteps) {
                ProgressCircle(step = step, currentStep = currentStep)
                if (step < totalSteps) {
                    ProgressLine(step = step, currentStep = currentStep, lineWidth = 44f)
                }
            }
        }
    }
}

@Composable
fun ProgressCircle(step: Int, currentStep: Int) {
    val borderCircleColor = if (step <= currentStep) LBAfternoonBlue else LBCardSoftBlue
    val textColor = if (step <= currentStep) Color.White else LBCardSoftBlue
    val backgroundCircle = if (step > currentStep) Color.White else LBAfternoonBlue
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(backgroundCircle, CircleShape)
            .border(2.dp, borderCircleColor, CircleShape)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        if (step < currentStep) {
            Icon(
                imageVector = Icons.Outlined.Check,
                contentDescription = null,
                tint = textColor
            )
        } else {
            Text(
                text = step.toString(),
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ProgressLine(step: Int, currentStep: Int, lineWidth: Float) {
    var lineColor:Color = if (step < currentStep) {
        LBAfternoonBlue
    }else {
        LBCardSoftBlue
    }
    Canvas(
        modifier = Modifier
            .height(2.dp)
            .width(lineWidth.dp)
    ) {
        drawLine(
            color = lineColor,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = 4.dp.toPx()
        )
    }
}

@Preview
@Composable
fun CustomProgressBarPreview() {
    LBProgressBar(currentStep = 4)
}
