package com.united.sportsmens.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.united.sportsmens.R
import com.united.sportsmens.models.GameModel
import com.united.sportsmens.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val model = intent.getParcelableExtra<GameModel>(KEY_MODEL)
        setData(model)
    }

    private fun setData(model: GameModel?) {
        if (model == null) return
        description.setText(model.descriptionRes)
        titleView.setText(model.titleRes)
        icon.setImageResource(model.iconRes)
    }

    companion object {

        private const val KEY_MODEL = "model"

        fun getInstance(context: Context, model: GameModel): Intent =
            Intent(context, DetailActivity::class.java)
                .putExtra(KEY_MODEL, model)
    }
}
