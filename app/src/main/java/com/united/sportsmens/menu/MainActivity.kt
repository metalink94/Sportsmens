package com.united.sportsmens.menu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.united.sportsmens.R
import com.united.sportsmens.models.Sportsmen
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val firstModel = Sportsmen(R.drawable.ic_1, getString(R.string.news_1), getString(R.string.news_1_desc))
        first.initModel(firstModel)
        first.setOnClickListener { onClick(firstModel) }
        val secondModel = Sportsmen(R.drawable.ic_2, getString(R.string.news_2), getString(R.string.news_2_desc))
        second.initModel(secondModel)
        second.setOnClickListener { onClick(secondModel) }
        val thirdModel = Sportsmen(R.drawable.ic_3, getString(R.string.news_3), getString(R.string.news_3_desc))
        third.initModel(thirdModel)
        third.setOnClickListener { onClick(thirdModel) }
    }

    private fun onClick(model: Sportsmen) {
        startActivity(DetailActivity.getInstance(this, model))
    }
}
