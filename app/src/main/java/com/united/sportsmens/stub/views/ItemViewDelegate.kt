package com.united.sportsmens.stub.views

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.united.sportsmens.stub.ItemModelView
import com.united.sportsmens.utils.adapter.DelegateAdapter
import com.united.sportsmens.views.ItemRowView

class ItemViewDelegate(private val listener: (model: ItemModelView) -> Unit): DelegateAdapter.Delegate<ItemModelView, ItemViewHolder>() {
    override fun createViewHolder(parent: ViewGroup): ItemViewHolder {
        val view = ItemRowView(parent.context)
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        return ItemViewHolder(view)
    }

    override fun bind(viewHolder: ItemViewHolder, model: ItemModelView) {
        viewHolder.bind(model)
        viewHolder.itemView.setOnClickListener { listener.invoke(model) }
    }
}

class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bind(model: ItemModelView) {
        val item = itemView as ItemRowView
        item.title = item.context.getString(model.titleRes)
        item.setBackgroundRes(model.backroundRes)
    }

}
