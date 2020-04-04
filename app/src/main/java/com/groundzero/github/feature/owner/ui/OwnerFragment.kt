package com.groundzero.github.feature.owner.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.groundzero.github.base.BaseFragment
import com.groundzero.github.databinding.FragmentOwnerBinding

class OwnerFragment : BaseFragment() {

    private val args: OwnerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentOwnerBinding.inflate(inflater, container, false).apply {
        owner = args.owner
    }.root
}
