package com.united.sportsmens.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.united.sportsmens.R
import com.united.sportsmens.models.GameModel
import com.united.sportsmens.models.Sportsmen
import kotlinx.android.synthetic.main.card.view.*

class CardItemView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(getContext(), R.layout.card, this)
    }

    var titleRow: CharSequence?
        get() = title.text
        set(value) {
            title.text = value
        }

    fun setIcon(iconRes: Int) {
        icon.setImageResource(iconRes)
    }

    fun initModel(model: Sportsmen) {
        titleRow = model.title
        setIcon(model.icon)
    }

    fun initModel(model: GameModel) {
        titleRow = context.getString(model.titleRes)
        setIcon(model.iconRes)
    }
}
