package li.yz.simplerichtextviewlib

import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

/**
 * desc: todo Overview
 * createed by liyuzheng on 2019/8/5 18:43
 */
abstract class BaseTag(mTag: String) {
    companion object {
        const val K_STRING = "{<string>}"
        const val K_LG_STRING="{<lgstring>}"
        const val K_DRAWABLE_IMG = "{<drawableimg>}"
        const val K_NETWORK_IMG = "{<networkimg>}"
        const val K_BITMAP = "{<bitmap>}"
    }

    val name: String = mTag
    open var tagSize: Int = mTag.length
    var click: ClickListener? = null
    val extras = mutableMapOf<String, String>()

    open fun toSpanedStr(): String {
        return name
    }

    open fun formatSpaned(spannableString: SpannableString, start: Int) {
        val end = start + tagSize
        click?.run {
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    this@run.onClick(widget, extras)
                }

                override fun updateDrawState(ds: TextPaint) {
//                    super.updateDrawState(ds)
                }
            }
            spannableString.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    open fun onCreated(view:RichTxtView){

    }

    open fun onDestroy(view:RichTxtView){

    }
}