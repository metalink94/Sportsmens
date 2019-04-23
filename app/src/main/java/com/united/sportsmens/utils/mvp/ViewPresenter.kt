package com.united.sportsmens.utils.mvp

import java.lang.ref.WeakReference

abstract class ViewPresenter<V : IView> {

    private var mViewRef: WeakReference<V>? = null

    fun setView(view: V) {
        mViewRef = WeakReference<V>(view)
    }

    protected fun getView(): V? {
        return if (mViewRef == null) null else mViewRef!!.get()
    }

    fun onAttachView() {

    }

    fun onDetachView() {
        if (mViewRef != null) {
            mViewRef!!.clear()
        }
    }
}
