package com.united.sportsmens.stub

import android.content.Intent
import android.os.Bundle
import com.united.sportsmens.R
import com.united.sportsmens.utils.BaseActivity
import kotlinx.android.synthetic.main.stub_first.*

class StubFirst: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stub_first)
        next.setOnClickListener { startActivity(Intent(this, StubSecond::class.java)) }
    }
}
