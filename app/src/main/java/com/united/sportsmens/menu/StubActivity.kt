package com.united.sportsmens.menu

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

        basketball.title = getString(R.string.basketball)
        basketball.setBackgroundRes(R.drawable.basketball)

        regbi.title = getString(R.string.regbi)
        regbi.setBackgroundRes(R.drawable.regbi)
    }
}
