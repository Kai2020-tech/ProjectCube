package com.goodideas.projectcube.ui.customView

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.AttributeSet
import android.webkit.WebSettings
import androidx.annotation.RequiresApi
import jp.wasabeef.richeditor.RichEditor

class MRich @JvmOverloads constructor(context: Context, attrs: AttributeSet)
    : RichEditor(context, attrs){

    override fun insertImage(url: String?, alt: String?) {
        exec("javascript:RE.prepareInsert();")
        exec("javascript:RE.insertHTML('<img src=\"$url\" alt=\"$alt\" width=\"100%\"><br><br>');")
    }

    override fun insertVideo(url: String?) {
        exec("javascript:RE.prepareInsert();")
        exec("javascript:RE.insertHTML('<video src=\"$url\" width=\"100%\" controls=\"\"></video>&nbsp');")
    }
    fun addNewLine(){
        exec("javascript:RE.prepareInsert();")
        exec("javascript:RE.insertHTML('<br>');")
    }
    fun addSpace(){
        exec("javascript:RE.prepareInsert();")
        exec("javascript:RE.insertHTML('&nbsp');")
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    fun themeChange(){
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                this.isForceDarkAllowed = true
                this.settings.forceDark = WebSettings.FORCE_DARK_ON
            }
            Configuration.UI_MODE_NIGHT_NO, Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                this.isForceDarkAllowed = false
                this.settings.forceDark = WebSettings.FORCE_DARK_OFF
            }
            else -> {
                this.settings.forceDark = WebSettings.FORCE_DARK_AUTO
            }
        }
    }
}