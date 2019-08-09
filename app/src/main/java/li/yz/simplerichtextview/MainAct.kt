package li.yz.simplerichtextview

//import kotlinx.android.synthetic.main.main_act.*
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.m_act.*
import li.yz.simplerichtextview.lib.ClickListener
import li.yz.simplerichtextview.lib.DrawableImageTag
import li.yz.simplerichtextview.lib.NetworkImageTag
import li.yz.simplerichtextview.lib.StringTag

/**
 * desc: todo Overview
 * createed by liyuzheng on 2019/8/9 17:42
 */
class MainAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.m_act)
//        gift1.setSpan()
//        btn.setOnClickListener {
//            ImageUtil.saveBitmapFromView(gift1)
//        }
        rich_txt.setText(
            StringTag("李李李李李李李").apply {
                textSize = 36
                textColor = Color.parseColor("#ff0000")
                typeFace = Typeface.DEFAULT_BOLD
                click = object : ClickListener {
                    override fun onClick(v: View, extras: MutableMap<String, String>) {
                        Toast.makeText(this@MainAct, "李", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            DrawableImageTag(this@MainAct, R.drawable.frame_shai_zi_1).apply {
                width = 10
                height = 20
                paddingStart=50
                paddingEnd=55
            },
            DrawableImageTag(this@MainAct, R.drawable.frame_shai_zi_2).apply {
                width = 29
                height = 39
                paddingStart=50
                paddingEnd=55
            },
            DrawableImageTag(this@MainAct, R.drawable.frame_shai_zi_1).apply {
                width = 29
                height = 39
                paddingEnd=50
            },
            NetworkImageTag(this@MainAct,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565354769259&di=7dbc748fcdb064b17dc3205fee3bb2eb&imgtype=0&src=http%3A%2F%2Fwww.yzqww.com%2Fuploadfile%2F2018%2F0809%2F20180809015801240.jpg",R.drawable.frame_shai_zi_2).apply {
//                width = 30
//                height =90
            },
            StringTag("张三张三张三张三张三张三张三张三张三张三张三张三张三张三张三张三").apply {
                extras["uid"] = "12333"
                textSize = 42
                textColor = Color.parseColor("#00574B")
                click = object : ClickListener {
                    override fun onClick(v: View, extras: MutableMap<String, String>) {
                        Toast.makeText(this@MainAct, extras["uid"], Toast.LENGTH_SHORT).show()
                    }
                }
            },
            DrawableImageTag(this@MainAct, R.drawable.frame_shai_zi_result_1).apply {
                width = 29
                height = 39
            })
    }
}