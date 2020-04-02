package com.groundzero.github.feature.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.groundzero.github.base.BaseFragment
import com.groundzero.github.common.Result
import com.groundzero.github.databinding.FragmentSearchBinding
import com.groundzero.github.di.helper.injectViewModel

class SearchFragment : BaseFragment() {

    private lateinit var viewModel: SearchViewModel
    private val adapter = SearchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentSearchBinding.inflate(inflater, container, false).apply {
        searchRepositoryRecyclerView.adapter = adapter
        viewModel = injectViewModel(viewModelFactory)
        viewModel.searchQuery("hello").observe(viewLifecycleOwner, Observer {
            println(it)
            when (it.status) {
                Result.Status.SUCCESS -> {
                    if (it.data != null) {
                        println("Submitting data ${it.data}")
                        adapter.submitList(it.data.repositories)
                    }
                }
            }
        })
    }.root
}
