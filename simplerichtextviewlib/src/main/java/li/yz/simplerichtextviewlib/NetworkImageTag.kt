package li.yz.simplerichtextviewlib

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableString
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

/**
 * desc: todo Overview
 * createed by liyuzheng on 2019/8/5 18:53
 */
class NetworkImageTag(
    context: Context,
    private val src: String,
    private val placeholderInt: Int = 0
) : ImageTag(context, K_NETWORK_IMG) {

    override fun formatSpaned(spannableString: SpannableString, start: Int) {
        super.formatSpaned(spannableString,start)
//        if (placeholder != 0) {
        val end = start + tagSize
//            val span = VerCenterImageSpan(context, getBitmapFromDrawable(placeholder))
//            spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }
        getBitmapFromNet(spannableString,start,end)
    }

    private fun getBitmapFromNet(spannableString: SpannableString, start: Int, end: Int) {
        Log.v("getBitmapFromNet", "getBitmapFromNet")
        Glide.with(context)
            .asBitmap()
            .load(src)
            .placeholder(placeholderInt)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {

                }

                override fun onLoadStarted(placeholder: Drawable?) {
                    placeholder?.run {
                        val span = VerCenterImageSpan(
                            context,
                            getBitmapFromDrawable(placeholderInt)
                        )
                        spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    }
                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Log.v("getBitmapFromNet", "onResourceReady")
                    val b = scaleBitmap(resource)
                    val imgSpan = VerCenterImageSpan(context, b)
                    spannableString.setSpan(imgSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            })
    }

}