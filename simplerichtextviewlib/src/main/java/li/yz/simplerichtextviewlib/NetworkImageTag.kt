package li.yz.simplerichtextviewlib

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableString
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import li.yz.simplerichtextviewlib.glide.GlideCircleWithBorderTransform

/**
 * desc: todo Overview
 * createed by liyuzheng on 2019/8/5 18:53
 */
class NetworkImageTag(
    context: Context,
    private val src: String,
    private val placeholderInt: Int = 0,
    private val transType: Int = TRANS_TYPE_DEFAULT
) : ImageTag(context, K_NETWORK_IMG) {
    companion object {
        const val TRANS_TYPE_DEFAULT = 0
        const val TRANS_TYPE_CIRCLE_CROP = 1
        const val TRANS_TYPE_CIRCLE_CROP_WITH_BORDER = 2
    }

    override fun formatSpaned(spannableString: SpannableString, start: Int) {
        super.formatSpaned(spannableString, start)
//        if (placeholder != 0) {
        val end = start + tagSize
//            val span = VerCenterImageSpan(context, getBitmapFromDrawable(placeholder))
//            spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }
        getBitmapFromNet(spannableString, start, end)
    }

    private fun getBitmapFromNet(spannableString: SpannableString, start: Int, end: Int) {
        Log.v("getBitmapFromNet", "getBitmapFromNet")
        var glide = Glide.with(context)
            .asBitmap()
            .load(src)
        if (width != -1 && height != -1) {
            glide = glide.override(width, height)
        }
        if (transType == TRANS_TYPE_CIRCLE_CROP) {
            glide = glide.circleCrop()
        } else if (transType == TRANS_TYPE_CIRCLE_CROP_WITH_BORDER) {
            glide = glide.transform(GlideCircleWithBorderTransform(strokeWidth, color))
        }
        glide.placeholder(placeholderInt)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadFailed(errorDrawable: Drawable?) {
                    val b = getBitmapFromDrawable(placeholderInt) ?: return
                    val imgSpan = VerCenterImageSpan(context, b)
                    spannableString.setSpan(imgSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }

                override fun onLoadCleared(placeholder: Drawable?) {

                }

                override fun onLoadStarted(placeholder: Drawable?) {
                    val bitmap = getBitmapFromDrawable(placeholderInt)
                    bitmap?.run {
                        val span = VerCenterImageSpan(
                            context,
                            this
                        )
                        spannableString.setSpan(
                            span,
                            start,
                            end,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
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

    var strokeWidth = 3
    var color = Color.WHITE
}