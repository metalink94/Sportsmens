package com.united.sportsmens.stub

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.united.sportsmens.R
import com.united.sportsmens.utils.BaseActivity
import kotlinx.android.synthetic.main.team_detail_activity.*

class TeamDetailActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_detail_activity)
        titleView.text = intent.getStringExtra(KEY_TITLE)
        textView.text = intent.getStringExtra(KEY_TEXT)
    }

    companion object {

        private const val KEY_TITLE = "title"
        private const val KEY_TEXT = "text"

        fun getIntent(context: Context, title: String, text: String) =
                Intent(context, TeamDetailActivity::class.java)
                    .putExtra(KEY_TITLE, title)
                    .putExtra(KEY_TEXT, text)
    }
}
