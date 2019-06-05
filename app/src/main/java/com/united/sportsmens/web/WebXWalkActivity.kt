package com.united.sportsmens.web

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.united.sportsmens.R
import android.graphics.Bitmap;
import android.graphics.Canvas
import android.util.Log
import android.view.TextureView;
import android.view.ViewGroup
import kotlinx.android.synthetic.main.xwalk_view.*
import org.xwalk.core.*

import java.net.CookieManager

class WebXWalkActivity: XWalkActivity() {
    override fun onXWalkReady() {

    }


    private val mXCookieManager: XWalkCookieManager = XWalkCookieManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // preferences
        XWalkPreferences.setValue(XWalkPreferences.ANIMATABLE_XWALK_VIEW, true)
        // layout
        setContentView(R.layout.xwalk_view)
        // touch listener
        xwalkview.setOnTouchListener { view, motionEvent ->
            Log.d(TAG, "onTouch: $motionEvent")
            false
        }
        // listeners
        setResourceClient()
        xwalkview.setUIClient(MyUIClient(xwalkview))
        // cookie manager
        mXCookieManager.setAcceptCookie(true)
        mXCookieManager.setAcceptFileSchemeCookies(true)
        mXCookieManager.setCookie("http://.vk.com/", "cookie=hi")
        // load url
        val url = intent.getStringExtra(URL) ?: getString(R.string.url)
        xwalkview.loadUrl(url)
    }

    private fun setResourceClient() {
        xwalkview.setResourceClient(object : XWalkResourceClient(xwalkview) {
            override fun onLoadStarted(view: XWalkView, url: String) {
                Log.d(TAG, "onLoadStarted: $url")
            }

            override fun onLoadFinished(view: XWalkView, url: String) {
                Log.d(TAG, "onLoadFinished: $url")
            }

            override fun onProgressChanged(view: XWalkView, newProgress: Int) {
                Log.d(TAG, "onProgressChanged: $newProgress")
            }

            override fun shouldOverrideUrlLoading(view: XWalkView, url: String): Boolean {
                Log.d(TAG, "shouldOverrideUrlLoading: $url")
                return super.shouldOverrideUrlLoading(view, url)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (xwalkview != null) {
            xwalkview.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onNewIntent(intent: Intent) {
        if (xwalkview != null) {
            xwalkview.onNewIntent(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        if (xwalkview != null) {
            xwalkview.pauseTimers()
            xwalkview.onHide()
        }
    }

    override fun onResume() {
        super.onResume()
        if (xwalkview != null) {
            xwalkview.resumeTimers()
            xwalkview.onShow()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (xwalkview != null) {
            xwalkview.onDestroy()
        }
    }

    override fun onBackPressed() {
        Log.d(TAG, "onBackPressed")
        super.onBackPressed()
    }

    /**
     * Example of default CookieManager and XWalkCookieManager sync
     *
     * @param cookieManager
     */
    private fun syncCookieStores(cookieManager: CookieManager) {
        val cookies = cookieManager.cookieStore.cookies
        if (cookies.size > 0) {
            mXCookieManager.removeExpiredCookie()

            for (cookie in cookies) {
                val builder = StringBuilder()
                if (cookie.secure) {
                    builder.append("https://")
                } else {
                    builder.append("http://")
                }
                builder.append(cookie.domain)
                builder.append(cookie.path)

                mXCookieManager.setCookie(builder.toString(), cookie.toString())
            }

            cookieManager.cookieStore.removeAll()
            mXCookieManager.flushCookieStore()
        }
    }

    /**
     * Returns default User-Agent
     *
     * @param webView
     * @return XWalkView default User-Agent
     */
    private fun getXWalkViewUserAgent(webView: XWalkView): String {
        //        return webView.getSettings().getUserAgentString();
        return webView.userAgentString
    }

    /**
     * Returns TextureView which is used in XWalkView
     *
     * @param group
     * @return
     */
    private fun findXWalkTextureView(group: ViewGroup): TextureView? {
        val childCount = group.childCount
        for (i in 0 until childCount) {
            val child = group.getChildAt(i)
            if (child is TextureView) {
                val parentClassName = child.getParent().javaClass.toString()
                val isRightKindOfParent = parentClassName.contains("XWalk")
                if (isRightKindOfParent) {
                    return child
                }
            } else if (child is ViewGroup) {
                val textureView = findXWalkTextureView(child)
                if (textureView != null) {
                    return textureView
                }
            }
        }

        return null
    }

    /**
     * Example of capturing image from XWalkView based on TextureView
     * <br></br><br></br>
     * Use XWalkView.captureBitmapAsync(XWalkGetBitmapCallback callback) instead of this method
     *
     * @return Image of view's content
     */
    @Deprecated("")
    fun captureImage(): Bitmap? {
        if (xwalkview != null) {
            var bitmap: Bitmap? = null

            var isCrosswalk = false
            try {
                Class.forName("org.xwalk.core.XWalkView")
                isCrosswalk = true
            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (isCrosswalk) {
                try {
                    val textureView = findXWalkTextureView(xwalkview)
                    bitmap = textureView!!.bitmap
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            } else {
                bitmap = Bitmap.createBitmap(xwalkview.width, xwalkview.height,
                    Bitmap.Config.ARGB_8888)
                val c = Canvas(bitmap!!)
                xwalkview.draw(c)
            }

            return bitmap
        } else {
            return null
        }
    }

    /**
     * Example of XWalkUIClient implementation
     */
    internal inner class MyUIClient(view: XWalkView) : XWalkUIClient(view) {

        override fun onPageLoadStarted(view: XWalkView, url: String) {
            Log.d(TAG, "onPageLoadStarted: $url")
        }

        override fun onPageLoadStopped(view: XWalkView, url: String, status: XWalkUIClient.LoadStatus) {
            Log.d(TAG, "onPageLoadStopped: $url, status: $status")

            /*if (status == XWalkUIClient.LoadStatus.FINISHED) {
                view.captureBitmapAsync(object : XWalkGetBitmapCallback() {
                    override fun onFinishGetBitmap(bitmap: Bitmap, i: Int) {
                        Log.d(TAG, "onFinishGetBitmap: $bitmap")
                    }
                })
            }*/
        }
    }

    companion object {

        val TAG = "XWalkViewCallbacks"
        private const val URL = "url"

        fun getInstance(context: Context, url: String?) : Intent =
                Intent(context, WebXWalkActivity::class.java)
                    .putExtra(URL, url)
    }
}
