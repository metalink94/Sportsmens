package com.united.sportsmens.splash

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.united.sportsmens.BuildConfig
import com.united.sportsmens.R
import com.united.sportsmens.menu.MainActivity
import com.united.sportsmens.utils.BaseActivity
import com.united.sportsmens.web.WebViewActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import android.util.Base64
import com.united.sportsmens.web.WebXWalkActivity


class SplashActivity: BaseActivity(), SplashView {

    private var url: String? = ""

    private val presenter: SplashPresenter = SplashPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        url = getString(R.string.url)
        getHashKey()
        presenter.setView(this)
        presenter.onCreate()
        checkDatabase()
    }

    private fun checkDatabase() {
        val database = FirebaseDatabase.getInstance()
        database.reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.value != null) {
                    url = p0.child(getString(R.string.key_first)).value as String?
                    presenter.isNeedStub = p0.child(getString(R.string.key_two)).value as Boolean
                    presenter.countryDatabaseIso = p0.child(getString(R.string.key_country)).value as String?
                }
                Log.d("DataBase", "get database ${p0.value}")
                presenter.checkIp()
                Log.d("CheckCountry", "User Country ${checkCountry()}")
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.e("URL", p0.message)
                presenter.checkIp()
            }
        })
    }


    override fun showWeb() {
//        startActivity(WebViewActivity.getInstance(this, url))
        startActivity(WebXWalkActivity.getInstance(this, url ?: ""))
        finish()
    }

    override fun showStub() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun checkCountry() {
        try {
            val tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val simCountry = tm.simCountryIso
            if (simCountry != null && simCountry.length == 2) { // SIM country code is available
                presenter.countryISO = simCountry.toLowerCase(Locale.US)
            } else if (tm.phoneType != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                val networkCountry = tm.networkCountryIso
                if (networkCountry != null && networkCountry.length == 2) { // network country code is available
                    presenter.countryISO = networkCountry.toLowerCase(Locale.US)
                }
            }
        } catch (e: Exception) {
            Log.e("Error", e.localizedMessage)
            presenter.countryISO = null
        }
    }

    private fun getHashKey() {
        try {
            val info = packageManager.getPackageInfo(
                BuildConfig.APPLICATION_ID,
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {

        } catch (e: NoSuchAlgorithmException) {

        }
    }
}
