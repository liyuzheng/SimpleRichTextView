package li.yz.simplerichtextview.lib

import android.view.View

/**
 * desc: todo Overview
 * createed by liyuzheng on 2019/8/5 18:49
 */
interface ClickListener {
    fun onClick(v: View, extras: MutableMap<String, String>)
}