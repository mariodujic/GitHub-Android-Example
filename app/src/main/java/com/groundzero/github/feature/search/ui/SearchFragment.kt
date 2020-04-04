package com.groundzero.github.feature.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.groundzero.github.R
import com.groundzero.github.base.BaseFragment
import com.groundzero.github.data.Result
import com.groundzero.github.databinding.FragmentSearchBinding
import com.groundzero.github.di.helper.injectViewModel
import com.groundzero.github.feature.search.data.Repository
import com.groundzero.github.view.RecyclerItemDecorator
import com.groundzero.github.view.toggleSideView

class SearchFragment : BaseFragment(), SearchListener {

    private val searchAdapter = SearchAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentSearchBinding.inflate(inflater, container, false).apply {
        searchRepositoryRecyclerView.apply {
            adapter = searchAdapter
            addItemDecoration(
                RecyclerItemDecorator(
                    resources.getDimension(R.dimen.minimal_padding).toInt()
                )
            )
        }
        val viewModel: SearchViewModel = injectViewModel(viewModelFactory)
        viewModel.also {
            observeSearchQuery(it)
            implementListeners(this, it)
            recyclerViewListener(this, it)
            setSideToggleButton(this, it)
            viewModel.setInitialQuery()
        }
    }.root

    private fun observeSearchQuery(viewModel: SearchViewModel) {
        viewModel.repositoryLive.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Result.Status.LOADING -> {
                    showLoadingDialog(R.string.loading_dialog_search_repository)
                }
                Result.Status.SUCCESS -> {
                    cancelLoadingScreen()
                    if (it.data != null) {
                        searchAdapter.submitList(it.data)
                    }
                    viewModel.isLoadingOnScroll = false
                }
                Result.Status.ERROR -> {
                    cancelLoadingScreen()
                    showToastMessage(R.string.warning_message_search_repository)
                    viewModel.isLoadingOnScroll = false
                }
            }
        })
    }

    private fun implementListeners(binding: FragmentSearchBinding, viewModel: SearchViewModel) {
        binding.searchRepositoryButton.setOnClickListener {
            binding.searchQuery.text.toString().also {
                if (it != "") {
                    viewModel.searchRepositories(it)
                } else {
                    showToastMessage(R.string.query_empty_warning)
                }
            }
        }
    }

    private fun recyclerViewListener(binding: FragmentSearchBinding, viewModel: SearchViewModel) {
        binding.searchRepositoryRecyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    if (!viewModel.isLoadingOnScroll) {
                        viewModel.nextPage()
                        viewModel.isLoadingOnScroll = true
                    }
                }
            }
        })
    }

    private fun setSideToggleButton(binding: FragmentSearchBinding, viewModel: SearchViewModel) {
        binding.apply {
            searchSortParent.setOnLongClickListener {
                if (viewModel.isToggleButtonShown) {
                    searchSortParent.toggleSideView(false).start()
                    viewModel.isToggleButtonShown = false
                } else {
                    searchSortParent.toggleSideView(true).start()
                    viewModel.isToggleButtonShown = true
                }
                true
            }

            searchSortParent.setOnClickListener {
                viewModel.nextSort()
            }

            viewModel.getSortTypeLive().observe(viewLifecycleOwner, Observer { sort ->
                searchSortType.text = sort.getType()
            })
        }
    }

    override fun onSearchItemClick(repository: Repository) {
        val action = SearchFragmentDirections.actionSearchFragmentToRepositoryFragment(repository)
        findNavController().navigate(action)
    }
}
