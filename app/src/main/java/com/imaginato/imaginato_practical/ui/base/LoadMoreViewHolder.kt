package com.imaginato.imaginato_practical.ui.base

import com.imaginato.imaginato_practical.databinding.ItemRecyclerLoaderBinding

class LoadMoreViewHolder<T>(
    private val binding: ItemRecyclerLoaderBinding
) : BaseRecyclerViewHolder<T>(binding.root) {
    override fun bind(item: T, position: Int) {
    }
}
