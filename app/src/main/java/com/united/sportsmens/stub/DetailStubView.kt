package com.united.sportsmens.stub

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.united.sportsmens.R
import com.united.sportsmens.stub.views.ItemViewDelegate
import com.united.sportsmens.utils.BaseActivity
import com.united.sportsmens.utils.adapter.DelegateAdapter
import com.united.sportsmens.utils.adapter.VerticalSpaceItemDecoration
import kotlinx.android.synthetic.main.detail_stub_activity.*

class DetailStubView : BaseActivity() {

    private val adapter = DelegateAdapter.Builder()
        .addDelegate(ItemModelView::class.java, ItemViewDelegate { onItemClick(it) })
        .build()

    private fun onItemClick(detail: ItemModelView) {
        startActivity(TeamDetailActivity.getIntent(this, getString(detail.titleRes), getString(detail.detailRes)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_stub_activity)
        initList()
        initViews()
    }

    private fun initViews() {
        if (intent.hasExtra(KEY_LIST)) {
            val listOfItems = intent.getParcelableArrayListExtra<ItemModelView>(KEY_LIST)
            adapter.addItems(listOfItems)
        }
    }

    private fun initList() {
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        list.addItemDecoration(VerticalSpaceItemDecoration(resources.getDimensionPixelOffset(R.dimen.item_padding)))
    }

    companion object {

        private const val KEY_LIST = "key_list"

        fun getIntent(context: Context, items: ArrayList<ItemModelView>) =
            Intent(context, DetailStubView::class.java)
                .putParcelableArrayListExtra(KEY_LIST, items)
    }
}
