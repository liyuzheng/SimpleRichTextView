package li.yz.simplerichtextviewlib

import android.graphics.*
import android.text.style.ReplacementSpan
import java.math.BigDecimal
import kotlin.math.roundToInt


/**
 * desc: todo Overview
 * createed by liyuzheng on 2020/6/4 16:11
 */
class LinearGradientFontSpan(private val config: LinearGradientFontConfig) : ReplacementSpan() {
    private var mSize = 0
    override fun getSize(
        paint: Paint,
        text: CharSequence?,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?
    ): Int {
        mSize = paint.measureText(text, start, end).roundToInt()
        val metrics: Paint.FontMetricsInt = paint.fontMetricsInt
        if (fm != null) {
            fm.top = metrics.top
            fm.ascent = metrics.ascent
            fm.descent = metrics.descent
            fm.bottom = metrics.bottom
        }
        return mSize
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence?,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        val colours = mutableListOf<Int>()
        config.color.forEach {
            val colorStr = if (it.length != 7 && it.length != 9) {
                "#000000"
            } else {
                it
            }
            colours.add(Color.parseColor(colorStr))
        }
        val width = mSize
        val height = paint.descent() - paint.ascent()
        val lg = LinearGradient(
            div(width, config.startX) + x,
            div(height.toInt(), config.startY) + y,
            div(width, config.endX) + x,
            div(height.toInt(), config.endY) + y,
            colours.toIntArray(),
            config.location.toFloatArray(),
            Shader.TileMode.CLAMP
        )
        paint.shader = lg
        canvas.drawText(text ?: "", start, end, x, y.toFloat(), paint) //绘制文字
    }

    private fun div(f1: Int, f2: Float): Float {
        if (f2 == 0f) {
            return 0f
        }
        val b1 = BigDecimal(f1.toString())
        val b2 = BigDecimal(f2.toString())
        return b1.multiply(b2).toFloat()
    }

    data class LinearGradientFontConfig(
        var color: MutableList<String> = mutableListOf(),
        var location: MutableList<Float> = mutableListOf(),
        var startX: Float = 0f,
        var startY: Float = 0f,
        var endX: Float = 0f,
        var endY: Float = 0f,
        var shadowColor: String = "#d1ffffff",
        var shadowRadius: Float = 0f // 透明度圆角
    )

}