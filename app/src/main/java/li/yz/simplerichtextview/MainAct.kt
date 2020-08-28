package li.yz.simplerichtextview

//import kotlinx.android.synthetic.main.main_act.*
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.m_act.*
import li.yz.simplerichtextviewlib.*

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
            StringTag("test red font").apply {
                textSize = 36
                typeFace = Typeface.DEFAULT_BOLD
                click = object : ClickListener {
                    override fun onClick(v: View, extras: MutableMap<String, String>) {
                        Toast.makeText(this@MainAct, "red font", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            DrawableImageTag(this@MainAct, R.drawable.frame_shai_zi_1).apply {
                paddingEnd = 50
                click = object : ClickListener {
                    override fun onClick(v: View, extras: MutableMap<String, String>) {
                        Toast.makeText(this@MainAct, "img drawable", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            NetworkImageTag(
                this@MainAct,
                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1697850329,1416483110&fm=26&gp=0.jpg",
                R.drawable.frame_shai_zi_2,
                NetworkImageTag.TRANS_TYPE_CIRCLE_CROP
            ).apply {
                width = 150
                height = 150
                click = object : ClickListener {
                    override fun onClick(v: View, extras: MutableMap<String, String>) {
                        Toast.makeText(this@MainAct, "img 1", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            NetworkImageTag(
                this@MainAct,
                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1752818900,208999761&fm=26&gp=0.jpg",
                R.drawable.frame_shai_zi_2,
                NetworkImageTag.TRANS_TYPE_CIRCLE_CROP_WITH_BORDER
            ).apply {
                width = 150
                height = 150
                click = object : ClickListener {
                    override fun onClick(v: View, extras: MutableMap<String, String>) {
                        Toast.makeText(this@MainAct, "img 2", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            LinearGradientStringTag(
                "Click me", LinearGradientFontSpan.LinearGradientFontConfig(
                    mutableListOf("#ff58c2f4", "#ffe3b466", "#ff5b3b90"),
                    mutableListOf(0.0f, 0.5f, 1.0f),
                    0f,
                    0.5f,
                    0.5f,
                    0.5f,
                    "#d1ffffff",
                    0.0f


                )
            ).apply {
                extras["str"] = "you click me"
                textSize = 42
                click = object : ClickListener {
                    override fun onClick(v: View, extras: MutableMap<String, String>) {
                        Toast.makeText(this@MainAct, extras["str"], Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }
}