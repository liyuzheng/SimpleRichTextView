package li.yz.simplerichtextviewlib

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.util.AttributeSet
import android.widget.TextView

/**
 * desc: todo Overview
 * createed by liyuzheng on 2019/8/9 13:06
 */
class RichTxtView : AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttr(context, attrs)attrs.xml
    }

    init {
        highlightColor = Color.TRANSPARENT
    }


    private fun initAttr(context: Context, attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.RichTxtView)
        val ignoreMovementMethod =
            ta.getBoolean(R.styleable.RichTxtView_ignoreMovementMethod, false)
        if (!ignoreMovementMethod) {
            movementMethod = LinkMovementMethod.getInstance()
        }
        ta.recycle()
    }

    fun setText(vararg tags: BaseTag) {
        val spannableString = convertTagToSpannableString(*tags)
        formatSpannableString(spannableString, *tags)
    }

    private fun convertTagToSpannableString(vararg tags: BaseTag): SpannableString {
        var spanStr = ""
        tags.forEach { tag ->
            spanStr += tag.toSpanedStr()
        }
        return SpannableString(spanStr)
    }

    private fun formatSpannableString(spannableString: SpannableString, vararg tags: BaseTag) {
        var i = 0
        tags.forEach { tag ->
            tag.formatSpaned(spannableString, i)
            i += tag.tagSize
        }
        text = spannableString
    }
}















