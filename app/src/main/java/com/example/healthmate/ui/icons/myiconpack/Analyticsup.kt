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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthmate.ui.icons.MyIconPack
import kotlin.Unit

public val MyIconPack.Analyticsup: ImageVector
    get() {
        if (_analyticsup != null) {
            return _analyticsup!!
        }
        _analyticsup = Builder(name = "Analyticsup", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF141B34)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(7.0f, 18.0f)
                verticalLineTo(16.0f)
                moveTo(12.0f, 18.0f)
                verticalLineTo(15.0f)
                moveTo(17.0f, 18.0f)
                verticalLineTo(13.0f)
                moveTo(2.5f, 12.0f)
                curveTo(2.5f, 7.522f, 2.5f, 5.282f, 3.891f, 3.891f)
                curveTo(5.282f, 2.5f, 7.522f, 2.5f, 12.0f, 2.5f)
                curveTo(16.478f, 2.5f, 18.718f, 2.5f, 20.109f, 3.891f)
                curveTo(21.5f, 5.282f, 21.5f, 7.522f, 21.5f, 12.0f)
                curveTo(21.5f, 16.478f, 21.5f, 18.718f, 20.109f, 20.109f)
                curveTo(18.718f, 21.5f, 16.478f, 21.5f, 12.0f, 21.5f)
                curveTo(7.522f, 21.5f, 5.282f, 21.5f, 3.891f, 20.109f)
                curveTo(2.5f, 18.718f, 2.5f, 16.478f, 2.5f, 12.0f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF141B34)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(5.992f, 11.486f)
                curveTo(8.147f, 11.558f, 13.034f, 11.233f, 15.814f, 6.821f)
                moveTo(13.992f, 6.288f)
                lineTo(15.868f, 5.986f)
                curveTo(16.096f, 5.957f, 16.432f, 6.138f, 16.514f, 6.353f)
                lineTo(17.01f, 7.991f)
            }
        }
        .build()
        return _analyticsup!!
    }

private var _analyticsup: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Analyticsup, contentDescription = "")
    }
}
