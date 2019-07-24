package com.united.sportsmens.stub

import android.os.Bundle
import com.united.sportsmens.R
import com.united.sportsmens.utils.BaseActivity
import kotlinx.android.synthetic.main.stub_second.*

class StubSecond: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stub_second)
        back.setOnClickListener {
            finish()
        }
    }
}
