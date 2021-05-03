package com.imaginato.imaginato_practical.ui.home

import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.imaginato.imaginato_practical.R
import com.imaginato.imaginato_practical.data.randomuser.entity.RandomUserItem
import com.imaginato.imaginato_practical.databinding.FragmentHomeBinding
import com.imaginato.imaginato_practical.extension.*
import com.imaginato.imaginato_practical.ui.base.BaseViewModelFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment :
    BaseViewModelFragment<HomeViewModel, FragmentHomeBinding>(R.layout.fragment_home),
    SwipeRefreshLayout.OnRefreshListener {

    override fun getClassName(): String {
        return this::class.java.simpleName
    }

    override fun isMultipleLoad() = false

    private val randomUserAdapter by lazy {
        RandomUserAdapter({ add, user ->
            viewModel.updateUserFavorite(add, user)
        }) {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it))
        }
    }

    override fun buildViewModel() = initActivityViewModel<HomeViewModel>()


    override fun initViews() {
        super.initViews()
        if (viewModel.randomUserLiveEvent.value.isNullOrEmpty()) {
            viewModel.getRandomUsers(true)
        }
        binding.rvRandomUser.adapter = randomUserAdapter
        binding.slListGrid.setOnRefreshListener(this)
        binding.rvRandomUser.loadMore(::isLoadMoreRequired) {
            viewModel.getRandomUsers()
        }
    }

    private fun isLoadMoreRequired(): Boolean {
        return viewModel.progressEvent.value != true
    }

    override fun initLiveDataObservers() {
        super.initLiveDataObservers()
        with(viewModel) {
            randomUserLiveEvent.observe(viewLifecycleOwner) {
                binding.pbDashboard.hideView()
                randomUserAdapter.hideLoadMore()
                randomUserAdapter.setData(it)
            }
            progressEvent.safeObserve(viewLifecycleOwner) {
                if (it) {
                    if (randomUserAdapter.itemCount == 0) {
                        binding.pbDashboard.showView()
                    } else {
                        randomUserAdapter.showLoadMore(RandomUserItem(""))
                    }
                }
            }
            errorEvent.safeObserve(viewLifecycleOwner) {
                binding.pbDashboard.hideView()
                randomUserAdapter.hideLoadMore()
                Timber.e(it)
            }
        }
    }

    override fun onRefresh() {
        binding.slListGrid.isRefreshing = false
        randomUserAdapter.clearAdapter()
        viewModel.getRandomUsers(true)
    }
}
