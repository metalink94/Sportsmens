package com.united.sportsmens.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.united.sportsmens.R
import com.united.sportsmens.utils.BaseActivity
import kotlinx.android.synthetic.main.stub_detail_activity.*
import kotlin.random.Random

class DetailStubActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stub_detail_activity)
        val team = intent.getParcelableExtra<IntentTeam>(KEY_TEAM)
        setTeam(team)
    }

    private fun setTeam(team: IntentTeam?) {
        if (team == null) return
        first.title = team.teamA
        second.title = team.teamB
        first.setBackgroundRes(team.imageA)
        second.setBackgroundRes(team.imageB)
        setScore()
    }

    private fun setScore() {
        score.text= "${Random.nextInt(0,5)} - ${Random.nextInt(0,5)}"
    }

    companion object {

        private const val KEY_TEAM = "team"

        fun getIntent(context: Context, intentTeam: IntentTeam) =
                Intent(context, DetailStubActivity::class.java)
                    .putExtra(KEY_TEAM, intentTeam)
    }
}
