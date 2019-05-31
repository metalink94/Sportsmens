package com.united.sportsmens.web

import java.io.IOException
import java.net.CookieManager
import java.net.CookiePolicy
import java.net.CookieStore
import java.net.URI
import java.util.Arrays

class WebkitCookieManagerProxy internal constructor(store: CookieStore?, cookiePolicy: CookiePolicy?) :
    CookieManager(null, cookiePolicy) {

    private var webkitCookieManager: android.webkit.CookieManager = android.webkit.CookieManager.getInstance()

    @Throws(IOException::class)
    override fun put(uri: URI?, responseHeaders: Map<String, List<String>>?) {
        // make sure our args are valid
        if (uri == null || responseHeaders == null) return

        // save our url once
        val url = uri.toString()

        // go over the headers
        for (headerKey in responseHeaders.keys) {
            // ignore headers which aren't cookie related
            if (headerKey == null || !(headerKey.equals(
                    "Set-Cookie2",
                    ignoreCase = true
                ) || headerKey.equals("Set-Cookie", ignoreCase = true))
            ) continue

            // process each of the headers
            for (headerValue in responseHeaders[headerKey]!!) {
                this.webkitCookieManager.setCookie(url, headerValue)
            }
        }
    }

    @Throws(IOException::class)
    override fun get(uri: URI?, requestHeaders: Map<String, List<String>>?): Map<String, List<String>> {
        // make sure our args are valid
        if (uri == null || requestHeaders == null) throw IllegalArgumentException("Argument is null")

        // save our url once
        val url = uri.toString()

        // prepare our response
        val res = java.util.HashMap<String, List<String>>()

        // get the cookie
        val cookie = this.webkitCookieManager.getCookie(url)

        // return it
        if (cookie != null) res["Cookie"] = Arrays.asList(cookie!!)
        return res
    }

    override fun getCookieStore(): CookieStore {
        // we don't want anyone to work with this cookie store directly
        throw UnsupportedOperationException()
    }
}
