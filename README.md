# SimpleRichTextView
a simple richtextview
[ ![Download](https://api.bintray.com/packages/liyz/lyzlib/SimpleRichTextView/images/download.svg?version=1.0.2) ](https://bintray.com/liyz/lyzlib/SimpleRichTextView/1.0.2/link)

### 效果图(图片录制失真，实际效果流畅）
<img src="/resources/simple.jpg" width = "300">

```
 rich_txt.setText(
            StringTag("test red font").apply {
                textSize = 36
                textColor = Color.parseColor("#ff0000")
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
                NetworkImageTag.TRANS_TYPE_DEFAULT
            ).apply {
                width = 150
                height = 150
                click = object : ClickListener {
                    override fun onClick(v: View, extras: MutableMap<String, String>) {
                        Toast.makeText(this@MainAct, "img 2", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            StringTag("Click me").apply {
                extras["str"] = "you click me"
                textSize = 42
                textColor = Color.parseColor("#00574B")
                click = object : ClickListener {
                    override fun onClick(v: View, extras: MutableMap<String, String>) {
                        Toast.makeText(this@MainAct, extras["str"], Toast.LENGTH_SHORT).show()
                    }
                }
            })
```
