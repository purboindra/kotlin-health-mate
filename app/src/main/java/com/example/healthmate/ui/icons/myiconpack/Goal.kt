package com.example.healthmate.ui.icons.myiconpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthmate.ui.icons.MyIconPack
import kotlin.Unit

public val MyIconPack.Goal: ImageVector
    get() {
        if (_goal != null) {
            return _goal!!
        }
        _goal = Builder(name = "Goal", defaultWidth = 25.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 25.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFC8C8C8)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(4.0f, 9.368f)
                curveTo(4.0f, 5.895f, 4.0f, 4.158f, 5.025f, 3.079f)
                curveTo(6.05f, 2.0f, 7.7f, 2.0f, 11.0f, 2.0f)
                horizontalLineTo(14.0f)
                curveTo(17.3f, 2.0f, 18.95f, 2.0f, 19.975f, 3.079f)
                curveTo(21.0f, 4.158f, 21.0f, 5.895f, 21.0f, 9.368f)
                verticalLineTo(14.632f)
                curveTo(21.0f, 18.105f, 21.0f, 19.842f, 19.975f, 20.921f)
                curveTo(18.95f, 22.0f, 17.3f, 22.0f, 14.0f, 22.0f)
                horizontalLineTo(11.0f)
                curveTo(7.7f, 22.0f, 6.05f, 22.0f, 5.025f, 20.921f)
                curveTo(4.0f, 19.842f, 4.0f, 18.105f, 4.0f, 14.632f)
                verticalLineTo(9.368f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFC8C8C8)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(13.5f, 11.0f)
                horizontalLineTo(17.0f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFC8C8C8)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(7.0f, 12.0f)
                curveTo(7.0f, 12.0f, 7.5f, 12.0f, 8.0f, 13.0f)
                curveTo(8.0f, 13.0f, 9.588f, 10.5f, 11.0f, 10.0f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFC8C8C8)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(14.0f, 17.0f)
                horizontalLineTo(17.5f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFC8C8C8)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(8.5f, 2.0f)
                lineTo(8.582f, 2.493f)
                curveTo(8.782f, 3.69f, 8.882f, 4.289f, 9.301f, 4.645f)
                curveTo(9.721f, 5.0f, 10.328f, 5.0f, 11.541f, 5.0f)
                horizontalLineTo(13.459f)
                curveTo(14.672f, 5.0f, 15.279f, 5.0f, 15.699f, 4.645f)
                curveTo(16.118f, 4.289f, 16.218f, 3.69f, 16.418f, 2.493f)
                lineTo(16.5f, 2.0f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFC8C8C8)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(8.5f, 17.0f)
                horizontalLineTo(9.5f)
            }
        }
        .build()
        return _goal!!
    }

private var _goal: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Goal, contentDescription = "")
    }
}
