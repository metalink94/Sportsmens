package com.united.sportsmens.stub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.united.sportsmens.R
import kotlinx.android.synthetic.main.stub_activity.*

class StubActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stub_activity)

        football.title = getString(R.string.football)
        football.setBackgroundRes(R.drawable.football)
        football.setOnClickListener { openDetailView(initFootbalClubs()) }

        basketball.title = getString(R.string.basketball)
        basketball.setBackgroundRes(R.drawable.basketball)
        basketball.setOnClickListener { openDetailView(initBasketClubs()) }

        regbi.title = getString(R.string.regbi)
        regbi.setBackgroundRes(R.drawable.regbi)
        regbi.setOnClickListener { openDetailView(initFootbalClubs()) }
    }

    private fun initFootbalClubs(): ArrayList<ItemModelView> {
        return arrayListOf(ItemModelView(R.string.arsenal, R.drawable.arsenal, R.string.arsenal_detail), ItemModelView(R.string.barselona, R.drawable.barselona, R.string.barselona_detail),
            ItemModelView(R.string.manchester, R.drawable.manchester, R.string.manchester_detail), ItemModelView(R.string.chelsea, R.drawable.chelsea, R.string.chelsea_detail),
            ItemModelView(R.string.cska, R.drawable.cska, R.string.cska_detail), ItemModelView(R.string.zenit, R.drawable.football, R.string.zenit_detail))
    }

    private fun initBasketClubs(): ArrayList<ItemModelView> {
        return arrayListOf(ItemModelView(R.string.cska, R.drawable.cska_b, R.string.cska_detail), ItemModelView(R.string.alba, R.drawable.alba, R.string.chelsea_detail),
            ItemModelView(R.string.unics, R.drawable.unics, R.string.arsenal_detail), ItemModelView(R.string.zalgiris, R.drawable.zalgiris, R.string.arsenal_detail)
        )
    }

    private fun openDetailView(list: ArrayList<ItemModelView>) {
        startActivity(DetailStubView.getIntent(this, list))
    }
}
