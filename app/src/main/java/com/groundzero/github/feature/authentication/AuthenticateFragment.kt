package com.groundzero.github.feature.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.groundzero.github.base.BaseFragment
import com.groundzero.github.databinding.FragmentAuthenticateBinding

class AuthenticateFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentAuthenticateBinding.inflate(inflater, container, false).apply {

        }.root
    }
}