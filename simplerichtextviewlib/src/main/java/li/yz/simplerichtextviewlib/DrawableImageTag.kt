package li.yz.simplerichtextviewlib

import android.content.Context
import android.text.Spannable
import android.text.SpannableString

/**
 * desc: todo Overview
 * createed by liyuzheng on 2019/8/5 18:53
 */
class DrawableImageTag(context: Context, private val drawableInt: Int) : ImageTag(context,
    K_DRAWABLE_IMG
) {
    override fun formatSpaned(spannableString: SpannableString, start: Int) {
        super.formatSpaned(spannableString,start)
        val end = start + tagSize
        val bitmap =  getBitmapFromDrawable(drawableInt)
        bitmap?.run{
            val span = VerCenterImageSpan(context, this)
            spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }


}