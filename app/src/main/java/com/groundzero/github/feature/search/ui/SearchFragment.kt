package com.groundzero.github.feature.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.groundzero.github.R
import com.groundzero.github.base.BaseFragment
import com.groundzero.github.common.Result
import com.groundzero.github.databinding.FragmentSearchBinding
import com.groundzero.github.di.helper.injectViewModel
import com.groundzero.github.feature.search.data.Repository

class SearchFragment : BaseFragment(), SearchListener {

    private val adapter = SearchAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentSearchBinding.inflate(inflater, container, false).apply {
        searchRepositoryRecyclerView.adapter = adapter
        val viewModel: SearchViewModel = injectViewModel(viewModelFactory)
        viewModel.also {
            observeSearchQuery("Android", it)
            implementListeners(this, it)
        }
    }.root

    private fun observeSearchQuery(query: String, viewModel: SearchViewModel) {
        viewModel.searchRepository(query, 1).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Result.Status.LOADING -> showLoadingDialog(R.string.loading_dialog_search_repository)
                Result.Status.SUCCESS -> {
                    cancelLoadingScreen()
                    if (it.data != null) {
                        adapter.submitList(it.data.repositories)
                    }
                }
                Result.Status.ERROR -> {
                    cancelLoadingScreen()
                    showToastMessage(R.string.warning_message_search_repository)
                }
            }
        })
    }

    private fun implementListeners(binding: FragmentSearchBinding, viewModel: SearchViewModel) {
        binding.searchRepositoryButton.setOnClickListener {
            observeSearchQuery(binding.searchQuery.text.toString(), viewModel)
        }
    }

    override fun onSearchItemClick(repository: Repository) {
        val action = SearchFragmentDirections.actionSearchFragmentToRepositoryFragment(repository)
        findNavController().navigate(action)
    }
}
