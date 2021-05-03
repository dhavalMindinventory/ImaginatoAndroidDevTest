package com.imaginato.imaginato_practical.ui.favorite

import androidx.navigation.fragment.findNavController
import com.imaginato.imaginato_practical.R
import com.imaginato.imaginato_practical.databinding.FragmentFavoriteBinding
import com.imaginato.imaginato_practical.extension.hideView
import com.imaginato.imaginato_practical.extension.initActivityViewModel
import com.imaginato.imaginato_practical.extension.showView
import com.imaginato.imaginato_practical.ui.base.BaseViewModelFragment
import com.imaginato.imaginato_practical.ui.home.HomeViewModel
import com.imaginato.imaginato_practical.ui.home.RandomUserAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment :
    BaseViewModelFragment<HomeViewModel, FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    override fun getClassName(): String {
        return this::class.java.simpleName
    }

    override fun isMultipleLoad() = false

    private val favoriteAdapter by lazy {
        RandomUserAdapter({ add, user ->
            viewModel.updateUserFavorite(add, user)
        }) {
            findNavController().navigate(
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailsFragment(
                    it
                )
            )
        }
    }

    override fun buildViewModel() = initActivityViewModel<HomeViewModel>()

    override fun initViews() {
        super.initViews()
        binding.rvfavorite.adapter = favoriteAdapter
    }

    override fun initLiveDataObservers() {
        super.initLiveDataObservers()
        viewModel.randomUserLiveEvent.observe(viewLifecycleOwner) {
            if (it != null) {
                val filter = it.filter { it.isFavorite }
                if (filter.isNullOrEmpty()) {
                    binding.tvNoData.showView()
                } else {
                    binding.tvNoData.hideView()
                }
                favoriteAdapter.addAll(filter, true)
            }
        }
    }

}
