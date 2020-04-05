package com.groundzero.github.feature.content.owner.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.groundzero.github.R
import com.groundzero.github.base.BaseFragment
import com.groundzero.github.data.Result
import com.groundzero.github.databinding.FragmentOwnerBinding
import com.groundzero.github.di.helper.injectViewModel

class OwnerFragment : BaseFragment() {

    private val args: OwnerFragmentArgs by navArgs()
    private lateinit var viewModel: OwnerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentOwnerBinding.inflate(inflater, container, false).apply {
        owner = args.owner

        viewModel = injectViewModel(viewModelFactory)
        viewModel.getOwner(args.owner.name!!).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Result.Status.LOADING -> showLoadingDialog(R.string.loading_more_owner_data)
                Result.Status.SUCCESS -> {
                    cancelLoadingScreen()
                    if (it.data != null) {
                        owner = it.data
                    }
                }
                Result.Status.ERROR -> {
                    if (it.message != null) {
                        showToastMessage(it.message)
                    } else {
                        showToastMessage(R.string.error_loading_more_owner_data)
                    }
                    cancelLoadingScreen()
                }
            }
        })

        args.owner.htmlUrl?.let { url ->
            ownerExternalUrl.setOnClickListener { openUrlInBrowser(url) }
        }
    }.root
}
