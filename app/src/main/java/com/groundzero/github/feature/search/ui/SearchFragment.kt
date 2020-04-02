package com.groundzero.github.feature.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.groundzero.github.base.BaseFragment
import com.groundzero.github.databinding.FragmentSearchBinding
import com.groundzero.github.di.helper.injectViewModel

class SearchFragment : BaseFragment() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentSearchBinding.inflate(inflater, container, false).apply {
        viewModel = injectViewModel(viewModelFactory)
        viewModel.searchQuery("hello").observe(viewLifecycleOwner, Observer {
            println(it)
        })
    }.root
}
