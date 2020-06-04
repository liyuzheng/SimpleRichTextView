package li.yz.simplerichtextviewlib

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan

/**
 * desc: todo Overview
 * createed by liyuzheng on 2020/6/4 15:35
 */
class LinearGradientStringTag(
    private val text: String,
    private val config: LinearGradientFontSpan.LinearGradientFontConfig?
) : BaseTag(K_LG_STRING) {
    override fun formatSpaned(spannableString: SpannableString, start: Int) {
        super.formatSpaned(spannableString, start)
        val end = start + tagSize
        if (textSize != 0) {
            val absoluteSizeSpan = AbsoluteSizeSpan(textSize)
            spannableString.setSpan(
                absoluteSizeSpan,
                start,
                end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        if (typeFace != Typeface.DEFAULT) {
            val styleSpan = StyleSpan(typeFace.style)
            spannableString.setSpan(styleSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        if (underLine) {
            val underlineSpan = UnderlineSpan()
            spannableString.setSpan(underlineSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val lgSpan = getRadiusGradientSpan()
        lgSpan?.run {
            spannableString.setSpan(lgSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    private fun getRadiusGradientSpan(): LinearGradientFontSpan? {
        if (config == null) return null
        return LinearGradientFontSpan(config)
    }


    override fun toSpanedStr(): String {
        return text
    }

    var textSize: Int = 0
    override var tagSize = text.length
    var typeFace: Typeface = Typeface.DEFAULT
    var underLine: Boolean = false


}