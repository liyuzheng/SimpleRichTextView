package li.yz.simplerichtextview.lib

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import li.yz.simplerichtextview.lib.BaseTag

/**
 * desc: todo Overview
 * createed by liyuzheng on 2019/8/5 18:50
 */
abstract class ImageTag(protected val context: Context, tag: String) : BaseTag(tag) {
    var width: Int = -1
    var height: Int = -1
    var paddingStart: Int = 0
    var paddingEnd: Int = 0

    protected fun scaleBitmap(bitmap: Bitmap): Bitmap {
        if (width == -1 || height == -1) return bitmap
        val newBitmap =
            Bitmap.createBitmap(width + paddingStart + paddingEnd, height, Bitmap.Config.ARGB_8888)//创建和目标相同大小的空Bitmap
        val canvas = Canvas(newBitmap)
        val paint = Paint()
        //针对绘制bitmap添加抗锯齿
        val pfd = PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
        paint.isFilterBitmap = true //对Bitmap进行滤波处理
        paint.isAntiAlias = true//设置抗锯齿
        canvas.drawFilter = pfd
        canvas.drawBitmap(bitmap, null, Rect(paddingStart, 0, width + paddingStart, height), paint)
        canvas.save()
//            // 存储
        canvas.restore()
        return newBitmap
    }

    protected fun getBitmapFromDrawable(drawableInt: Int): Bitmap {
        val res: Resources = context.resources
        val bitmapDrawable = res.getDrawable(drawableInt) as BitmapDrawable
        return scaleBitmap(bitmapDrawable.bitmap)
    }
}