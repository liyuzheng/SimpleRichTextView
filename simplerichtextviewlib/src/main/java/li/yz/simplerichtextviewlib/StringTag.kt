package li.yz.simplerichtextviewlib

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.annotation.ColorInt

/**
 * desc: todo Overview
 * createed by liyuzheng on 2019/8/5 18:43
 */
class StringTag(private val text: String) : BaseTag(K_STRING) {
    override fun formatSpaned(spannableString: SpannableString, start: Int) {
        super.formatSpaned(spannableString, start)
        val end = start + tagSize
        if (textColor != 0) {
            val forcegroundColorSpan = ForegroundColorSpan(textColor)
            spannableString.setSpan(forcegroundColorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        if (textSize != 0) {
            val absoluteSizeSpan = AbsoluteSizeSpan(textSize)
            spannableString.setSpan(absoluteSizeSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        if (typeFace != Typeface.DEFAULT) {
            val styleSpan = StyleSpan(typeFace.style)
            spannableString.setSpan(styleSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        if (underLine) {
            val underlineSpan = UnderlineSpan()
            spannableString.setSpan(underlineSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    override fun toSpanedStr(): String {
        return text
    }

    var textSize: Int = 0
    override var tagSize = text.length
    var typeFace: Typeface = Typeface.DEFAULT
    @ColorInt
    var textColor: Int = 0
    var underLine: Boolean = false
}