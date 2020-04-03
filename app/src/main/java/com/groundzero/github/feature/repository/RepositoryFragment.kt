package com.groundzero.github.feature.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.groundzero.github.base.BaseFragment
import com.groundzero.github.databinding.FragmentRepositoryBinding

class RepositoryFragment : BaseFragment() {

    private val args: RepositoryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentRepositoryBinding.inflate(inflater, container, false).apply {
        repository = args.repository
    }.root
}
