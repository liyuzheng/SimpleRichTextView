package li.yz.simplerichtextview.lib

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.AppCompatTextView
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.util.AttributeSet

/**
 * desc: todo Overview
 * createed by liyuzheng on 2019/8/9 13:06
 */
class RichTxtView : AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    init {
        movementMethod = LinkMovementMethod.getInstance()
        highlightColor = Color.TRANSPARENT
    }

    fun setText(vararg tags: BaseTag) {
        val spannableString = convertTagToSpannableString(*tags)
        this.setText(spannableString, BufferType.SPANNABLE)
        formatSpannableString(*tags)
    }

    private fun convertTagToSpannableString(vararg tags: BaseTag): SpannableString {
        var spanStr = ""
        tags.forEach { tag ->
            spanStr += tag.toSpanedStr()
        }
        return SpannableString(spanStr)
    }

    private fun formatSpannableString(vararg tags: BaseTag) {
        val spannableString: SpannableString =
            if (text is SpannableString) text as SpannableString else SpannableString("")
        var i = 0
        tags.forEach { tag ->
            tag.formatSpaned(spannableString, i)
            i += tag.tagSize
        }
    }
}














