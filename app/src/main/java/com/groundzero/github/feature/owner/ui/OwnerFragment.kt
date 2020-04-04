package com.groundzero.github.feature.owner.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.groundzero.github.base.BaseFragment
import com.groundzero.github.databinding.FragmentOwnerBinding
import com.groundzero.github.di.helper.injectViewModel

class OwnerFragment : BaseFragment() {

    private val args: OwnerFragmentArgs by navArgs()
    private lateinit var viewModel: OwnerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentOwnerBinding.inflate(inflater, container, false).apply {
        owner = args.repositoryOwner

        viewModel = injectViewModel(viewModelFactory)
        viewModel.getOwner(args.repositoryOwner.name!!).observe(viewLifecycleOwner, Observer {
            println(it)
        })
    }.root
}
