package com.united.sportsmens.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.cardview.widget.CardView
import com.united.sportsmens.R
import kotlinx.android.synthetic.main.item_row.view.*

class ItemRowView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    CardView(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.item_row, this)
    }

    var title: CharSequence
        get() = titleView.text
        set(value) {
            titleView.text = value
        }

    fun setBackgroundRes(@DrawableRes imageRes: Int) {
        conteiner.setBackgroundResource(imageRes)
    }
}
