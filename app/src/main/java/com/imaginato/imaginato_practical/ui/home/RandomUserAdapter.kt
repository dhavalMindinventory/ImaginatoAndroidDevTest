package com.imaginato.imaginato_practical.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.imaginato.imaginato_practical.R
import com.imaginato.imaginato_practical.data.randomuser.entity.RandomUserItem
import com.imaginato.imaginato_practical.databinding.ItemRecyclerLoaderBinding
import com.imaginato.imaginato_practical.databinding.ItemRecyclerUserBinding
import com.imaginato.imaginato_practical.ui.base.BaseRecyclerViewAdapter
import com.imaginato.imaginato_practical.ui.base.BaseRecyclerViewHolder
import com.imaginato.imaginato_practical.ui.base.LoadMoreViewHolder
import java.util.*

class RandomUserAdapter constructor(
    private val favoriteClick: (add: Boolean, user: RandomUserItem) -> Unit,
    private val itemClick: (user: RandomUserItem) -> Unit,
) :
    BaseRecyclerViewAdapter<RandomUserItem, BaseRecyclerViewHolder<RandomUserItem>>() {

    companion object {
        private const val ITEM = 0
        private const val LOADING = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == items.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    override fun createItemViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<RandomUserItem> {
        return if (viewType == LOADING) {
            LoadMoreViewHolder(
                ItemRecyclerLoaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            RandomUserViewHolder(
                ItemRecyclerUserBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun bindItemViewHolder(holder: BaseRecyclerViewHolder<RandomUserItem>, position: Int) {
        holder.bind(items[position], position)
    }

    fun setData(it: ArrayList<RandomUserItem>) {
        addAll(it, true)
    }

    inner class RandomUserViewHolder(
        private val binding: ItemRecyclerUserBinding
    ) : BaseRecyclerViewHolder<RandomUserItem>(binding.root) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: RandomUserItem, position: Int) {
            with(item) {
                binding.tvName.text = "${name?.title} ${name?.first} ${name?.last}"
                binding.tvEmail.text = email
                binding.tvCity.text = location?.city

                Glide.with(binding.ivUserThumb)
                    .load(picture?.thumbnail)
                    .transform(
                        RoundedCorners(
                            binding.ivUserThumb.context.resources.getDimensionPixelSize(
                                R.dimen._28sdp
                            )
                        )
                    )
                    .into(binding.ivUserThumb)
                binding.ivFavorite.isSelected = isFavorite
                binding.ivFavorite.setOnClickListener {
                    favoriteClick.invoke(!isFavorite, this)
                    notifyItemChanged(position)
                }
                binding.root.setOnClickListener {
                    itemClick.invoke(this)
                }
            }
        }
    }
}
