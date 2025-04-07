package com.example.healthmate.ui.icons.myiconpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthmate.ui.icons.MyIconPack
import kotlin.Unit

public val MyIconPack.Cardio: ImageVector
    get() {
        if (_cardio != null) {
            return _cardio!!
        }
        _cardio = Builder(name = "Cardio", defaultWidth = 100.0.dp, defaultHeight = 100.0.dp,
                viewportWidth = 100.0f, viewportHeight = 100.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(90.0f, 46.51f)
                horizontalLineToRelative(-0.71f)
                curveToRelative(1.43f, -2.97f, 2.21f, -6.24f, 2.21f, -9.63f)
                curveToRelative(0.0f, -6.0f, -2.35f, -11.62f, -6.61f, -15.84f)
                curveToRelative(-8.81f, -8.72f, -23.15f, -8.72f, -31.97f, 0.0f)
                lineTo(50.0f, 23.93f)
                lineToRelative(-2.93f, -2.9f)
                curveToRelative(-8.81f, -8.72f, -23.15f, -8.72f, -31.97f, 0.0f)
                curveToRelative(-4.26f, 4.22f, -6.61f, 9.84f, -6.61f, 15.84f)
                curveToRelative(0.0f, 3.4f, 0.78f, 6.66f, 2.21f, 9.63f)
                horizontalLineTo(10.0f)
                curveToRelative(-0.83f, 0.0f, -1.5f, 0.67f, -1.5f, 1.5f)
                reflectiveCurveToRelative(0.67f, 1.5f, 1.5f, 1.5f)
                horizontalLineToRelative(2.43f)
                curveToRelative(0.79f, 1.13f, 1.67f, 2.21f, 2.68f, 3.2f)
                lineToRelative(31.91f, 31.58f)
                curveToRelative(0.82f, 0.81f, 1.9f, 1.22f, 2.98f, 1.22f)
                reflectiveCurveToRelative(2.16f, -0.41f, 2.98f, -1.22f)
                lineToRelative(31.91f, -31.58f)
                curveToRelative(1.0f, -0.99f, 1.89f, -2.07f, 2.68f, -3.2f)
                horizontalLineTo(90.0f)
                curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f)
                reflectiveCurveTo(90.83f, 46.51f, 90.0f, 46.51f)
                close()
                moveTo(11.5f, 36.87f)
                curveToRelative(0.0f, -5.19f, 2.03f, -10.06f, 5.72f, -13.71f)
                curveToRelative(3.83f, -3.79f, 8.85f, -5.68f, 13.87f, -5.68f)
                curveToRelative(5.02f, 0.0f, 10.05f, 1.89f, 13.87f, 5.68f)
                lineToRelative(3.98f, 3.94f)
                curveToRelative(0.58f, 0.58f, 1.53f, 0.58f, 2.11f, 0.0f)
                lineToRelative(3.98f, -3.94f)
                curveToRelative(7.65f, -7.57f, 20.1f, -7.57f, 27.75f, 0.0f)
                curveToRelative(3.69f, 3.65f, 5.72f, 8.52f, 5.72f, 13.71f)
                curveToRelative(0.0f, 3.44f, -0.91f, 6.74f, -2.59f, 9.63f)
                horizontalLineTo(70.0f)
                curveToRelative(-0.65f, 0.0f, -1.22f, 0.41f, -1.42f, 1.03f)
                lineToRelative(-2.52f, 7.56f)
                lineToRelative(-6.62f, -21.53f)
                curveToRelative(-0.19f, -0.63f, -0.78f, -1.06f, -1.43f, -1.06f)
                curveToRelative(0.0f, 0.0f, -0.01f, 0.0f, -0.02f, 0.0f)
                curveToRelative(-0.66f, 0.01f, -1.24f, 0.45f, -1.43f, 1.09f)
                lineToRelative(-3.69f, 12.91f)
                horizontalLineTo(50.0f)
                curveToRelative(-0.7f, 0.0f, -1.31f, 0.49f, -1.46f, 1.17f)
                lineToRelative(-2.51f, 11.29f)
                lineToRelative(-4.56f, -21.28f)
                curveToRelative(-0.14f, -0.67f, -0.72f, -1.16f, -1.41f, -1.18f)
                curveToRelative(-0.69f, -0.02f, -1.3f, 0.41f, -1.5f, 1.07f)
                lineTo(33.8f, 53.46f)
                lineToRelative(-2.4f, -6.01f)
                curveToRelative(-0.23f, -0.57f, -0.78f, -0.94f, -1.39f, -0.94f)
                horizontalLineTo(14.09f)
                curveTo(12.41f, 43.61f, 11.5f, 40.32f, 11.5f, 36.87f)
                close()
                moveTo(82.78f, 50.58f)
                lineTo(50.87f, 82.15f)
                curveToRelative(-0.48f, 0.48f, -1.26f, 0.48f, -1.74f, 0.0f)
                lineTo(17.22f, 50.58f)
                curveToRelative(-0.35f, -0.34f, -0.66f, -0.71f, -0.98f, -1.07f)
                horizontalLineToRelative(12.75f)
                lineToRelative(3.62f, 9.06f)
                curveToRelative(0.24f, 0.59f, 0.81f, 0.96f, 1.46f, 0.94f)
                curveToRelative(0.64f, -0.03f, 1.19f, -0.46f, 1.37f, -1.07f)
                lineToRelative(4.32f, -14.4f)
                lineToRelative(4.78f, 22.29f)
                curveToRelative(0.15f, 0.69f, 0.76f, 1.18f, 1.46f, 1.19f)
                curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.01f, 0.0f)
                curveToRelative(0.7f, 0.0f, 1.31f, -0.49f, 1.46f, -1.17f)
                lineToRelative(3.74f, -16.83f)
                horizontalLineTo(54.0f)
                curveToRelative(0.67f, 0.0f, 1.26f, -0.44f, 1.44f, -1.09f)
                lineToRelative(2.61f, -9.14f)
                lineToRelative(6.51f, 21.17f)
                curveToRelative(0.19f, 0.62f, 0.76f, 1.05f, 1.42f, 1.06f)
                curveToRelative(0.67f, 0.0f, 1.23f, -0.41f, 1.44f, -1.03f)
                lineToRelative(3.66f, -10.97f)
                horizontalLineToRelative(12.68f)
                curveTo(83.44f, 49.87f, 83.13f, 50.23f, 82.78f, 50.58f)
                close()
            }
        }
        .build()
        return _cardio!!
    }

private var _cardio: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Cardio, contentDescription = "")
    }
}
