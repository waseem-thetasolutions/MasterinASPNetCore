package com.theta.masterinaspnetcore.utils

import android.os.Build
import android.text.Html
import android.text.Spanned

class Helpers {

    companion object{
        fun getStringFromHtml(html : String?) : Spanned {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)

            return Html.fromHtml(html)
        }
    }


}