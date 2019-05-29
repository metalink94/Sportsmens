package com.united.sportsmens.menu

import android.support.v7.app.AppCompatActivity
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
    }

    private fun addThirdRow() {
        val teamLeft = Team(R.drawable.ic_5, getString(R.string.england))
        val teamRight = Team(R.drawable.ic_6, getString(R.string.afganistan))
        val thirdResult = ResultModel(teamLeft, getString(R.string.score_3), teamRight, getString(R.string.description_3))
        third.initModel(thirdResult)
        third.setOnClickListener { onClick(thirdResult) }
    }

    private fun addSecondRow() {
        val teamLeft = Team(R.drawable.ic_3, getString(R.string.australlia))
        val teamRight = Team(R.drawable.ic_4, getString(R.string.sri_lanka))
        val secondResult = ResultModel(teamLeft, getString(R.string.score_2), teamRight, getString(R.string.description_2))
        second.initModel(secondResult)
        second.setOnClickListener { onClick(secondResult) }
    }

    private fun addFirstRow() {
        val teamLeft = Team(R.drawable.ic_1, getString(R.string.india))
        val teamRight = Team(R.drawable.ic_2, getString(R.string.bangladesh))
        val firstResult = ResultModel(teamLeft, getString(R.string.score_1), teamRight, getString(R.string.description_1))
        first.initModel(firstResult)
        first.setOnClickListener { onClick(firstResult) }
    }

    private fun onClick(model: ResultModel) {
        startActivity(DetailActivity.getInstance(this, model))
    }
}
