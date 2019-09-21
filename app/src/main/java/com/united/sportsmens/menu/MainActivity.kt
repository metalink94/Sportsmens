package com.united.sportsmens.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.united.sportsmens.R
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
        addFourthRow()
    }

    private fun addFourthRow() {
        val teamLeft = Team(R.drawable.ic_7, getString(R.string.sri_lanka2), getString(R.string.coef_7))
        val teamRight = Team(R.drawable.ic_8, getString(R.string.south_africa), getString(R.string.coef_8))
        val fourthModel = ResultModel(teamLeft, getString(R.string.score_4),
            teamRight, getString(R.string.description_4), getString(R.string.detail_descr_4))
        fourth.initModel(fourthModel)
        fourth.setOnClickListener { onClick(fourthModel) }
    }

    private fun addThirdRow() {
        val teamLeft = Team(R.drawable.ic_4, getString(R.string.england), getString(R.string.coef_5))
        val teamRight = Team(R.drawable.ic_6, getString(R.string.afganistan), getString(R.string.coef_6))
        val thirdResult = ResultModel(teamLeft, getString(R.string.score_3),
            teamRight, getString(R.string.description_3), getString(R.string.detail_descr_3))
        third.initModel(thirdResult)
        third.setOnClickListener { onClick(thirdResult) }
    }

    private fun addSecondRow() {
        val teamLeft = Team(R.drawable.ic_3, getString(R.string.australlia), getString(R.string.coef_3))
        val teamRight = Team(R.drawable.ic_5, getString(R.string.sri_lanka), getString(R.string.coef_4))
        val secondResult = ResultModel(teamLeft, getString(R.string.score_2),
            teamRight, getString(R.string.description_2), getString(R.string.detail_descr_2))
        second.initModel(secondResult)
        second.setOnClickListener { onClick(secondResult) }
    }

    private fun addFirstRow() {
        val teamLeft = Team(R.drawable.ic_2, getString(R.string.india), getString(R.string.coef_1))
        val teamRight = Team(R.drawable.ic_1, getString(R.string.bangladesh), getString(R.string.coef_2))
        val firstResult = ResultModel(teamLeft, getString(R.string.score_1),
            teamRight, getString(R.string.description_1), getString(R.string.detail_descr_1))
        first.initModel(firstResult)
        first.setOnClickListener { onClick(firstResult) }
    }

    private fun onClick(model: ResultModel) {
        startActivity(DetailActivity.getInstance(this, model))
    }
}
