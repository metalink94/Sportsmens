package com.united.sportsmens.menu

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.united.sportsmens.R
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.stub_activity.*

class StubActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stub_activity)

        real.title = getString(R.string.real)
        real.setBackgroundRes(R.drawable.real)

        manchester.title = getString(R.string.manchester)
        manchester.setBackgroundRes(R.drawable.manchester)

        firstLayout.setOnClickListener { startDetail(IntentTeam(getString(R.string.real), getString(R.string.manchester),
            R.drawable.real, R.drawable.manchester)) }

        cska.title = getString(R.string.cska)
        cska.setBackgroundRes(R.drawable.cska)

        chelsea.title = getString(R.string.chealse)
        chelsea.setBackgroundRes(R.drawable.chelsea)

        secondLayout.setOnClickListener { startDetail(IntentTeam(getString(R.string.cska), getString(R.string.chealse),
            R.drawable.cska, R.drawable.chelsea)) }

        barselona.title = getString(R.string.barselona)
        barselona.setBackgroundRes(R.drawable.barselona)

        arsenal.title = getString(R.string.arsenal)
        arsenal.setBackgroundRes(R.drawable.arsenal)

        thirdLayout.setOnClickListener { startDetail(IntentTeam(getString(R.string.barselona), getString(R.string.arsenal),
            R.drawable.barselona, R.drawable.arsenal)) }

    }

    private fun startDetail(intentTeam: IntentTeam) {
        startActivity(DetailStubActivity.getIntent(this, intentTeam))
    }
}

@Parcelize
class IntentTeam(val teamA:String, val teamB: String, val imageA: Int, val imageB: Int): Parcelable
