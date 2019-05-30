package com.united.sportsmens.menu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.united.sportsmens.R
import com.united.sportsmens.models.GameModel
import com.united.sportsmens.models.ResultModel
import com.united.sportsmens.models.Team
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        addFirstRow()
        addSecondRow()
        addThirdRow()
        addFouthRow()
    }

    private fun addFouthRow() {
        val fourhGame = GameModel(R.drawable.ic_4, R.string.name_4, R.string.description_4)
        fourth.initModel(fourhGame)
        fourth.setOnClickListener { onClick(fourhGame) }
    }

    private fun addThirdRow() {
        val thirdGame = GameModel(R.drawable.ic_3, R.string.name_3, R.string.description_3)
        third.initModel(thirdGame)
        third.setOnClickListener { onClick(thirdGame) }
    }

    private fun addSecondRow() {
        val secondGame = GameModel(R.drawable.ic_2, R.string.name_2, R.string.description_2)
        second.initModel(secondGame)
        second.setOnClickListener { onClick(secondGame) }
    }

    private fun addFirstRow() {
        val firstGame = GameModel(R.drawable.ic_1, R.string.name_1, R.string.description_1)
        first.initModel(firstGame)
        first.setOnClickListener { onClick(firstGame) }
    }

    private fun onClick(model: GameModel) {
        startActivity(DetailActivity.getInstance(this, model))
    }
}
