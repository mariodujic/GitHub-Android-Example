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

    private lateinit var viewModel: SearchViewModel
    private val adapter = SearchAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentSearchBinding.inflate(inflater, container, false).apply {
        searchRepositoryRecyclerView.adapter = adapter
        viewModel = injectViewModel(viewModelFactory)
        viewModel.searchQuery("hello").observe(viewLifecycleOwner, Observer {
            println(it)
            when (it.status) {
                Result.Status.LOADING -> showLoadingDialog(R.string.loading_dialog_search_repository)
                Result.Status.SUCCESS -> {
                    cancelLoadingScreen()
                    if (it.data != null) {
                        println("Submitting data ${it.data}")
                        adapter.submitList(it.data.repositories)
                    }
                }
                Result.Status.ERROR -> {
                    cancelLoadingScreen()
                    showToastMessage(R.string.warning_message_search_repository)
                }
            }
        })
    }.root

    override fun onSearchItemClick(repository: Repository) {
        val action = SearchFragmentDirections.actionSearchFragmentToRepositoryFragment(repository)
        findNavController().navigate(action)
    }
}
