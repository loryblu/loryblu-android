import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

@Composable
fun CustomProgressBar(currentStep: Int) {
    val totalSteps = 4

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 20.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (step in 1..totalSteps) {
                val isCurrentStep = step == currentStep
                ProgressCircle(step = step, isCurrentStep = isCurrentStep)

                if (step < totalSteps) {
                    Spacer(modifier = Modifier.weight(1f))
                    val lineWidth = calculateLineWidth(currentStep, step)
                    ProgressLine(isCurrentStep = isCurrentStep, lineWidth = lineWidth)
                }
            }
        }
    }
}

@Composable
fun ProgressCircle(step: Int, isCurrentStep: Boolean) {
    val circleColor = if (isCurrentStep) Color.Green else Color.Gray
    val textColor = if (isCurrentStep) Color.White else Color.Black

    Box(
        modifier = Modifier
            .size(40.dp)
            .background(circleColor, CircleShape)
            .border(2.dp, Color.Black, CircleShape)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        if (isCurrentStep) {
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
fun ProgressLine(isCurrentStep: Boolean, lineWidth: Float) {
    val lineColor = if (isCurrentStep) Color.Green else Color.Gray

    Canvas(
        modifier = Modifier
            .height(2.dp)
            .width(lineWidth.dp)
    ) {
        drawLine(
            color = lineColor,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = 2.dp.toPx()
        )
    }
}

fun calculateLineWidth(currentStep: Int, step: Int): Float {
    return 50f
}

@Preview
@Composable
fun CustomProgressBarPreview() {
    CustomProgressBar(currentStep = 4)
}
