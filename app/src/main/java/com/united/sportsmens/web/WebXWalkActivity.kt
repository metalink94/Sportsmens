package com.united.sportsmens.web

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.united.sportsmens.R
import android.graphics.Bitmap;
import android.util.Log;
import android.view.TextureView;
import android.view.ViewGroup;
import com.united.sportsmens.utils.BaseActivity
import kotlinx.android.synthetic.main.xwalk_view.*

import org.xwalk.core.XWalkCookieManager;
import org.xwalk.core.XWalkGetBitmapCallback;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;
import org.xwalk.core.XWalkWebResourceRequest;
import org.xwalk.core.XWalkWebResourceResponse;

import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.List;

class WebXWalkActivity: BaseActivity() {

    private val mXCookieManager: XWalkCookieManager = XWalkCookieManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XWalkPreferences.setValue(XWalkPreferences.ANIMATABLE_XWALK_VIEW, true)
        setContentView(R.layout.xwalk_view)
        xwalkview.setResourceClient(MyResourceClient(xwalkview))
        xwalkview.setUIClient(MyUIClient(xwalkview))
        // cookie manager
        mXCookieManager.setAcceptCookie(true)
        mXCookieManager.setAcceptFileSchemeCookies(true)
        xwalkview.loadUrl(intent.getStringExtra(KEY_URL))
    }

    companion object {

        private const val KEY_URL = "url"

        fun getInstance(context: Context, url: String): Intent = Intent(context, WebXWalkActivity::class.java)
            .putExtra(KEY_URL, url)
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

}

/**
 * Example of XWalkResourceClient implementation
 */
internal class MyResourceClient(view: XWalkView) : XWalkResourceClient(view) {

    val TAG = "XWalkViewCallbacks"

    override fun onLoadStarted(view: XWalkView, url: String) {
        Log.w(TAG, "onLoadStarted: $url")
    }

    override fun onLoadFinished(view: XWalkView, url: String) {
        Log.w(TAG, "onLoadFinished: $url")
    }

    override fun onProgressChanged(view: XWalkView, newProgress: Int) {
        Log.w(TAG, "onProgressChanged: $newProgress")
    }

    override fun shouldOverrideUrlLoading(view: XWalkView, url: String): Boolean {
        Log.w(TAG, "shouldOverrideUrlLoading: $url")
        return super.shouldOverrideUrlLoading(view, url)
    }

    override fun shouldInterceptLoadRequest(
        view: XWalkView,
        request: XWalkWebResourceRequest
    ): XWalkWebResourceResponse {
        Log.w(
            TAG, "shouldInterceptLoadRequest: url: " + request.url
                    + ", method: " + request.method
        )
        return super.shouldInterceptLoadRequest(view, request)
    }
}

/**
 * Example of XWalkUIClient implementation
 */
internal class MyUIClient(view: XWalkView) : XWalkUIClient(view) {

    val TAG = "XWalkViewCallbacks"

    override fun onPageLoadStarted(view: XWalkView?, url: String?) {
        super.onPageLoadStarted(view, url)
        Log.w(TAG, "onPageLoadStarted: $url")
    }

    override fun onPageLoadStopped(view: XWalkView, url: String, status: LoadStatus) {
        Log.w(TAG, "onPageLoadStopped: $url, status: $status")

        if (status == LoadStatus.FINISHED) {
            view.captureBitmapAsync(object : XWalkGetBitmapCallback() {
                override fun onFinishGetBitmap(bitmap: Bitmap, i: Int) {
                    Log.w(TAG, "onFinishGetBitmap: $bitmap")
                }
            })
        }
    }
}
