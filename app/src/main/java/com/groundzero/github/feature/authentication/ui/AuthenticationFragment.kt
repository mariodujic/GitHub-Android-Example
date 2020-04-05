package com.groundzero.github.feature.authentication.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.groundzero.github.R
import com.groundzero.github.base.BaseFragment
import com.groundzero.github.databinding.FragmentAuthenticationBinding
import com.groundzero.github.di.helper.injectViewModel

class AuthenticationFragment : BaseFragment() {

    private val clientId = "fb983e17e40930f28a29"
    private val clientSecret = "2f1ef8f13b359b026684d9b9b494353866bf7faf"
    private lateinit var redirectUrl: String

    private lateinit var viewModel: AuthenticationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentAuthenticationBinding.inflate(inflater, container, false).apply {
            viewModel = injectViewModel(viewModelFactory)
            redirectUrl =
                "${getString(R.string.manifest_scheme)}://${getString(R.string.manifest_host)}"

            authenticateWithGithub.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(viewModel.getOAuthUrl(clientId, redirectUrl))
                    )
                )
            }
        }.root
    }

    override fun onResume() {
        super.onResume()
        val uri = requireActivity().intent.data
        if (viewModel.verifyOAuthResponse(uri, getString(R.string.manifest_scheme))) {
            val code = viewModel.getCode(uri!!)
            println(code)
        }
        println(uri)
    }
}