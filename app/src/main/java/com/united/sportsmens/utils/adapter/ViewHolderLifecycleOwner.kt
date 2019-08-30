package com.united.sportsmens.utils.adapter

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

open class ViewHolderLifecycleOwner(override val containerView: View) : ViewHolder(containerView), LifecycleOwner {

    @Suppress("LeakingThis")
    private val lifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.markState(Lifecycle.State.INITIALIZED)
    }

    fun markAttach() {
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }

    fun markDetach() {
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}

open class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer
