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
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
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

public val MyIconPack.Profile: ImageVector
    get() {
        if (_profile != null) {
            return _profile!!
        }
        _profile = Builder(name = "Profile", defaultWidth = 25.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 25.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFC8C8C8)),
                    strokeLineWidth = 1.5f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(3.0f, 12.0f)
                curveTo(3.0f, 7.522f, 3.0f, 5.282f, 4.391f, 3.891f)
                curveTo(5.782f, 2.5f, 8.022f, 2.5f, 12.5f, 2.5f)
                curveTo(16.978f, 2.5f, 19.218f, 2.5f, 20.609f, 3.891f)
                curveTo(22.0f, 5.282f, 22.0f, 7.522f, 22.0f, 12.0f)
                curveTo(22.0f, 16.478f, 22.0f, 18.718f, 20.609f, 20.109f)
                curveTo(19.218f, 21.5f, 16.978f, 21.5f, 12.5f, 21.5f)
                curveTo(8.022f, 21.5f, 5.782f, 21.5f, 4.391f, 20.109f)
                curveTo(3.0f, 18.718f, 3.0f, 16.478f, 3.0f, 12.0f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFC8C8C8)),
                    strokeLineWidth = 1.5f, strokeLineCap = Round, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(8.0f, 17.0f)
                curveTo(10.332f, 14.558f, 14.643f, 14.443f, 17.0f, 17.0f)
                moveTo(14.995f, 9.5f)
                curveTo(14.995f, 10.881f, 13.874f, 12.0f, 12.491f, 12.0f)
                curveTo(11.109f, 12.0f, 9.988f, 10.881f, 9.988f, 9.5f)
                curveTo(9.988f, 8.119f, 11.109f, 7.0f, 12.491f, 7.0f)
                curveTo(13.874f, 7.0f, 14.995f, 8.119f, 14.995f, 9.5f)
                close()
            }
        }
        .build()
        return _profile!!
    }

private var _profile: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Profile, contentDescription = "")
    }
}
