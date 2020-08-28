package li.yz.simplerichtextviewlib.glide

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import java.security.MessageDigest

/**
 * desc: todo Overview
 * createed by liyuzheng on 2020/8/28 11:16
 */
class GlideCircleWithBorderTransform(private val pStrokeWidth: Int, private val pColor: Int) :
    BitmapTransformation() {

    private val mBorderPaint = Paint().apply {
        isDither = true
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = pStrokeWidth.toFloat()
        color = pColor
    }

    private fun drawStroke(bmp: Bitmap) {
        val canvas = Canvas(bmp)
        val center = bmp.width.toFloat() / 2 // 圆心
        val radius = bmp.width.toFloat() / 2 - pStrokeWidth / 2 // 半径
        canvas.drawCircle(center, center, radius, mBorderPaint)
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        // 使用 glide 自己的 circleCrop
        val bitmap = TransformationUtils.circleCrop(pool, toTransform, outWidth, outHeight)
        // 自己画白边
        drawStroke(bitmap)
        return bitmap
    }

}