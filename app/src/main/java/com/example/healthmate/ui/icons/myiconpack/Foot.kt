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

public val MyIconPack.Foot: ImageVector
    get() {
        if (_foot != null) {
            return _foot!!
        }
        _foot = Builder(name = "Foot", defaultWidth = 32.0.dp, defaultHeight = 32.0.dp,
                viewportWidth = 32.0f, viewportHeight = 32.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(21.715f, 10.993f)
                curveToRelative(0.082f, -1.13f, -0.19f, -2.04f, -0.812f, -2.722f)
                curveToRelative(-1.116f, -1.223f, -3.015f, -1.656f, -4.883f, -1.656f)
                curveToRelative(-1.286f, 0.0f, -2.558f, 0.205f, -3.55f, 0.499f)
                curveToRelative(-1.062f, 0.314f, -2.044f, 0.845f, -2.728f, 1.7f)
                curveToRelative(-1.307f, 1.633f, -0.826f, 3.959f, -0.333f, 5.804f)
                curveToRelative(0.555f, 2.08f, 1.471f, 4.152f, 1.612f, 6.32f)
                curveToRelative(0.014f, 0.215f, 0.016f, 0.43f, 0.01f, 0.646f)
                arcToRelative(17.141f, 17.141f, 0.0f, false, true, -0.176f, 1.877f)
                curveToRelative(-0.093f, 0.684f, -0.202f, 1.366f, -0.296f, 2.05f)
                arcToRelative(21.47f, 21.47f, 0.0f, false, false, -0.201f, 2.04f)
                curveToRelative(-0.026f, 0.616f, -0.012f, 1.241f, 0.108f, 1.848f)
                curveToRelative(0.104f, 0.528f, 0.296f, 1.047f, 0.632f, 1.474f)
                curveToRelative(0.629f, 0.798f, 1.631f, 1.139f, 2.663f, 1.139f)
                curveToRelative(1.074f, 0.0f, 2.181f, -0.369f, 2.931f, -0.976f)
                curveToRelative(1.22f, -0.986f, 1.651f, -2.355f, 1.427f, -3.856f)
                curveToRelative(-0.269f, -1.807f, -0.578f, -3.62f, -0.581f, -5.453f)
                curveToRelative(-0.003f, -2.004f, -0.199f, -4.318f, 1.001f, -6.06f)
                curveToRelative(1.088f, -1.577f, 3.02f, -2.54f, 3.176f, -4.674f)
                close()
                moveTo(16.894f, 14.531f)
                curveToRelative(-1.418f, 2.058f, -1.386f, 4.516f, -1.361f, 6.492f)
                curveToRelative(0.003f, 0.239f, 0.006f, 0.474f, 0.007f, 0.706f)
                curveToRelative(0.003f, 1.775f, 0.262f, 3.485f, 0.512f, 5.139f)
                lineToRelative(0.091f, 0.605f)
                curveToRelative(0.171f, 1.149f, -0.321f, 1.695f, -0.706f, 2.006f)
                curveToRelative(-0.393f, 0.318f, -1.066f, 0.531f, -1.674f, 0.531f)
                curveToRelative(-0.239f, 0.0f, -0.824f, -0.037f, -1.093f, -0.377f)
                curveToRelative(-0.101f, -0.128f, -0.187f, -0.35f, -0.241f, -0.624f)
                curveToRelative(-0.073f, -0.368f, -0.096f, -0.805f, -0.072f, -1.377f)
                curveToRelative(0.027f, -0.64f, 0.108f, -1.296f, 0.184f, -1.852f)
                curveToRelative(0.045f, -0.329f, 0.094f, -0.657f, 0.142f, -0.985f)
                curveToRelative(0.053f, -0.355f, 0.105f, -0.71f, 0.154f, -1.066f)
                curveToRelative(0.089f, -0.657f, 0.175f, -1.366f, 0.193f, -2.096f)
                arcToRelative(8.901f, 8.901f, 0.0f, false, false, -0.014f, -0.827f)
                curveToRelative(-0.114f, -1.741f, -0.624f, -3.328f, -1.116f, -4.863f)
                arcToRelative(37.7f, 37.7f, 0.0f, false, true, -0.559f, -1.843f)
                curveToRelative(-0.405f, -1.515f, -0.725f, -3.179f, -0.038f, -4.038f)
                curveToRelative(0.362f, -0.452f, 0.945f, -0.799f, 1.734f, -1.032f)
                arcToRelative(11.0f, 11.0f, 0.0f, false, true, 2.983f, -0.417f)
                curveToRelative(1.582f, 0.0f, 2.823f, 0.366f, 3.405f, 1.004f)
                curveToRelative(0.153f, 0.167f, 0.349f, 0.477f, 0.295f, 1.228f)
                curveToRelative(-0.048f, 0.656f, -0.515f, 1.155f, -1.335f, 1.98f)
                curveToRelative(-0.482f, 0.487f, -1.029f, 1.036f, -1.491f, 1.706f)
                close()
                moveTo(20.44f, 6.54f)
                curveToRelative(0.197f, 0.076f, 0.409f, 0.116f, 0.622f, 0.116f)
                curveToRelative(1.122f, 0.0f, 2.207f, -0.997f, 2.697f, -2.477f)
                curveToRelative(0.272f, -0.822f, 0.317f, -1.685f, 0.12f, -2.431f)
                curveTo(23.667f, 0.932f, 23.204f, 0.35f, 22.566f, 0.111f)
                arcTo(1.833f, 1.833f, 0.0f, false, false, 21.949f, 0.0f)
                curveToRelative(-1.127f, 0.0f, -2.213f, 0.997f, -2.697f, 2.472f)
                curveToRelative(-0.283f, 0.851f, -0.322f, 1.737f, -0.105f, 2.493f)
                curveToRelative(0.22f, 0.783f, 0.677f, 1.342f, 1.293f, 1.575f)
                close()
                moveTo(20.202f, 2.784f)
                curveTo(20.542f, 1.75f, 21.276f, 1.0f, 21.949f, 1.0f)
                curveToRelative(0.086f, 0.0f, 0.183f, 0.018f, 0.266f, 0.048f)
                curveToRelative(0.432f, 0.162f, 0.621f, 0.666f, 0.697f, 0.955f)
                curveToRelative(0.147f, 0.557f, 0.111f, 1.218f, -0.102f, 1.862f)
                curveToRelative(-0.343f, 1.038f, -1.078f, 1.791f, -1.748f, 1.791f)
                arcToRelative(0.733f, 0.733f, 0.0f, false, true, -0.268f, -0.052f)
                curveToRelative(-0.417f, -0.158f, -0.607f, -0.637f, -0.685f, -0.915f)
                curveToRelative(-0.162f, -0.561f, -0.129f, -1.236f, 0.093f, -1.905f)
                close()
                moveTo(16.651f, 4.936f)
                curveToRelative(0.066f, 0.019f, 0.154f, 0.03f, 0.234f, 0.03f)
                curveToRelative(0.677f, 0.0f, 1.335f, -0.734f, 1.526f, -1.709f)
                curveToRelative(0.234f, -1.138f, -0.186f, -2.188f, -0.949f, -2.387f)
                arcToRelative(1.104f, 1.104f, 0.0f, false, false, -0.24f, -0.027f)
                curveToRelative(-0.709f, 0.0f, -1.325f, 0.704f, -1.531f, 1.699f)
                curveToRelative(-0.126f, 0.646f, -0.054f, 1.292f, 0.207f, 1.758f)
                curveToRelative(0.181f, 0.33f, 0.449f, 0.554f, 0.753f, 0.636f)
                close()
                moveTo(16.672f, 2.745f)
                curveToRelative(0.131f, -0.633f, 0.448f, -0.902f, 0.552f, -0.902f)
                verticalLineToRelative(-0.001f)
                curveToRelative(0.117f, 0.059f, 0.35f, 0.528f, 0.207f, 1.222f)
                curveToRelative(-0.114f, 0.58f, -0.445f, 0.884f, -0.522f, 0.905f)
                curveToRelative(-0.013f, -0.003f, -0.072f, -0.039f, -0.137f, -0.157f)
                curveToRelative(-0.141f, -0.251f, -0.178f, -0.665f, -0.1f, -1.067f)
                close()
                moveTo(13.353f, 4.798f)
                curveToRelative(0.224f, 0.232f, 0.496f, 0.357f, 0.766f, 0.357f)
                lineTo(14.132f, 5.155f)
                arcToRelative(0.782f, 0.782f, 0.0f, false, false, 0.196f, -0.029f)
                curveToRelative(0.564f, -0.155f, 0.945f, -0.917f, 0.881f, -1.774f)
                curveToRelative(-0.065f, -0.996f, -0.649f, -1.772f, -1.326f, -1.772f)
                horizontalLineToRelative(-0.012f)
                curveToRelative(-0.713f, 0.035f, -1.155f, 0.825f, -1.093f, 1.799f)
                curveToRelative(0.043f, 0.567f, 0.251f, 1.09f, 0.575f, 1.419f)
                close()
                moveTo(13.908f, 2.613f)
                curveToRelative(0.107f, 0.1f, 0.275f, 0.377f, 0.303f, 0.813f)
                arcToRelative(1.31f, 1.31f, 0.0f, false, true, -0.123f, 0.692f)
                lineToRelative(-0.023f, -0.023f)
                curveToRelative(-0.115f, -0.116f, -0.259f, -0.394f, -0.289f, -0.78f)
                curveToRelative(-0.024f, -0.373f, 0.067f, -0.607f, 0.132f, -0.702f)
                close()
                moveTo(11.425f, 6.115f)
                arcToRelative(0.776f, 0.776f, 0.0f, false, false, 0.234f, -0.03f)
                arcToRelative(1.07f, 1.07f, 0.0f, false, false, 0.638f, -0.542f)
                curveToRelative(0.214f, -0.385f, 0.262f, -0.897f, 0.142f, -1.398f)
                curveToRelative(-0.19f, -0.776f, -0.732f, -1.324f, -1.314f, -1.324f)
                arcToRelative(0.91f, 0.91f, 0.0f, false, false, -0.251f, 0.035f)
                curveToRelative(-0.328f, 0.088f, -0.589f, 0.362f, -0.731f, 0.736f)
                arcToRelative(2.16f, 2.16f, 0.0f, false, false, -0.049f, 1.206f)
                curveToRelative(0.187f, 0.751f, 0.759f, 1.317f, 1.331f, 1.317f)
                close()
                moveTo(11.078f, 3.948f)
                arcToRelative(0.516f, 0.516f, 0.0f, false, true, 0.061f, -0.117f)
                curveToRelative(0.078f, 0.044f, 0.246f, 0.214f, 0.328f, 0.547f)
                curveToRelative(0.074f, 0.309f, 0.026f, 0.554f, -0.052f, 0.694f)
                curveToRelative(-0.005f, 0.009f, -0.009f, 0.017f, -0.014f, 0.023f)
                curveToRelative(-0.095f, -0.06f, -0.261f, -0.239f, -0.331f, -0.526f)
                curveToRelative(-0.054f, -0.228f, -0.049f, -0.462f, 0.008f, -0.621f)
                close()
                moveTo(9.142f, 7.479f)
                lineToRelative(0.179f, -0.024f)
                curveToRelative(0.29f, -0.081f, 0.508f, -0.303f, 0.622f, -0.612f)
                curveToRelative(0.109f, -0.291f, 0.12f, -0.64f, 0.038f, -0.979f)
                curveToRelative(-0.152f, -0.624f, -0.61f, -1.079f, -1.084f, -1.079f)
                lineToRelative(-0.218f, 0.036f)
                curveToRelative(-0.251f, 0.07f, -0.469f, 0.297f, -0.583f, 0.606f)
                curveToRelative(-0.11f, 0.291f, -0.126f, 0.63f, -0.045f, 0.979f)
                curveToRelative(0.154f, 0.617f, 0.611f, 1.073f, 1.091f, 1.073f)
                close()
            }
        }
        .build()
        return _foot!!
    }

private var _foot: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Foot, contentDescription = "")
    }
}
