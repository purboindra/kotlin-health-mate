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
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthmate.ui.icons.MyIconPack
import kotlin.Unit

public val MyIconPack.Home: ImageVector
    get() {
        if (_home != null) {
            return _home!!
        }
        _home = Builder(name = "Home", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF0BBC47)),
                    strokeLineWidth = 1.5f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(8.999f, 22.0f)
                lineTo(8.749f, 18.491f)
                curveTo(8.614f, 16.605f, 10.108f, 15.0f, 11.999f, 15.0f)
                curveTo(13.891f, 15.0f, 15.385f, 16.605f, 15.25f, 18.491f)
                lineTo(14.999f, 22.0f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF0BBC47)),
                    strokeLineWidth = 1.5f, strokeLineCap = Butt, strokeLineJoin = Round,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(2.352f, 13.214f)
                curveTo(1.998f, 10.916f, 1.822f, 9.768f, 2.256f, 8.749f)
                curveTo(2.691f, 7.731f, 3.654f, 7.034f, 5.581f, 5.641f)
                lineTo(7.021f, 4.6f)
                curveTo(9.418f, 2.867f, 10.617f, 2.0f, 12.0f, 2.0f)
                curveTo(13.383f, 2.0f, 14.582f, 2.867f, 16.979f, 4.6f)
                lineTo(18.419f, 5.641f)
                curveTo(20.346f, 7.034f, 21.31f, 7.731f, 21.744f, 8.749f)
                curveTo(22.178f, 9.768f, 22.002f, 10.916f, 21.649f, 13.214f)
                lineTo(21.348f, 15.172f)
                curveTo(20.847f, 18.429f, 20.597f, 20.057f, 19.429f, 21.029f)
                curveTo(18.261f, 22.0f, 16.554f, 22.0f, 13.139f, 22.0f)
                horizontalLineTo(10.861f)
                curveTo(7.446f, 22.0f, 5.739f, 22.0f, 4.571f, 21.029f)
                curveTo(3.403f, 20.057f, 3.153f, 18.429f, 2.653f, 15.172f)
                lineTo(2.352f, 13.214f)
                close()
            }
        }
        .build()
        return _home!!
    }

private var _home: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Home, contentDescription = "")
    }
}
