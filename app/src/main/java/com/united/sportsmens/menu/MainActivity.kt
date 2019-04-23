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
        val firstModel = Sportsmen(R.drawable.ic_1, getString(R.string.kohli), getString(R.string.kohli_desc))
        first.initModel(firstModel)
        first.setOnClickListener { onClick(firstModel) }
        val secondModel = Sportsmen(R.drawable.ic_2, getString(R.string.sharma), getString(R.string.sharma_desc))
        second.initModel(secondModel)
        second.setOnClickListener { onClick(secondModel) }
        val thirdModel = Sportsmen(R.drawable.ic_3, getString(R.string.ms), getString(R.string.ms_desc))
        third.initModel(thirdModel)
        third.setOnClickListener { onClick(thirdModel) }
        val fourthModel = Sportsmen(R.drawable.ic_4, getString(R.string.rahul), getString(R.string.rahul_desc))
        four.initModel(fourthModel)
        four.setOnClickListener { onClick(fourthModel) }
    }

    private fun onClick(model: Sportsmen) {
        startActivity(DetailActivity.getInstance(this, model))
    }
}
