package com.garden.taskgarden.RecyclerView

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
* @created 14/May/2021 - 11:38 AM
* @project TaskGarden
* @author Blake MacDade
*/
class PaddingItemDecoration (private val padding: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top=padding
    }
}