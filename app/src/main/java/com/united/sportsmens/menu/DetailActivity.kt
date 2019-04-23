package com.united.sportsmens.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.united.sportsmens.R
import com.united.sportsmens.models.Sportsmen
import com.united.sportsmens.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val model = intent.getParcelableExtra<Sportsmen>(KEY_MODEL)
        setData(model)
    }

    private fun setData(model: Sportsmen?) {
        if (model == null) return
        icon.setImageResource(model.icon)
        titleView.text = model.title
        description.text = model.description
    }

    companion object {

        private const val KEY_MODEL = "model"

        fun getInstance(context: Context, model: Sportsmen): Intent =
                Intent(context, DetailActivity::class.java)
                    .putExtra(KEY_MODEL, model)
    }
}
