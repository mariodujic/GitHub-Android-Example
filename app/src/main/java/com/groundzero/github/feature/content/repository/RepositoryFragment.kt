package com.groundzero.github.feature.content.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.groundzero.github.base.BaseFragment
import com.groundzero.github.databinding.FragmentRepositoryBinding
import com.groundzero.github.feature.content.owner.data.Owner

class RepositoryFragment : BaseFragment() {

    private val args: RepositoryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentRepositoryBinding.inflate(inflater, container, false).apply {
        repository = args.repository
        repositoryMoreOwner.setOnClickListener { onRepositoryOwnerClick(args.repository.owner!!) }
        args.repository.htmlUrl?.let { url ->
            repositoryExternalUrl.setOnClickListener { openUrlInBrowser(url) }
        }
    }.root


    private fun onRepositoryOwnerClick(owner: Owner) {
        val action = RepositoryFragmentDirections.actionRepositoryFragmentToOwnerFragment(owner)
        findNavController().navigate(action)
    }
}
