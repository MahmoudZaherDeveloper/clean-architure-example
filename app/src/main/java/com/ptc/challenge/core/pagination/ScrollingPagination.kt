package com.ptc.challenge.core.pagination

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class ScrollingPagination(private val gridLayoutManager: GridLayoutManager) :
    RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (limitReached()) {
            loadMoreDate()
        }
    }

    private fun limitReached(): Boolean {
        val visibleItemCount: Int = gridLayoutManager.childCount
        val totalItemCount: Int = gridLayoutManager.itemCount
        val firstVisibleItemPosition: Int = gridLayoutManager.findFirstVisibleItemPosition()

        return (visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0

    }

    abstract fun loadMoreDate()

}