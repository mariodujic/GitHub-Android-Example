package com.groundzero.github.feature.authentication.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.groundzero.github.BuildConfig
import com.groundzero.github.R
import com.groundzero.github.base.BaseFragment
import com.groundzero.github.data.Result
import com.groundzero.github.databinding.FragmentAuthenticationBinding
import com.groundzero.github.di.helper.injectViewModel
import com.groundzero.github.feature.content.common.MainActivity

class AuthenticationFragment : BaseFragment() {

    private val clientId = BuildConfig.CLIENT_ID
    private val clientSecret = BuildConfig.CLIENT_SECRET
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

            continueWithoutAuthentication.setOnClickListener {
                viewModel.removeAccessToken()
                nextActivity(MainActivity::class.java)
            }
        }.root
    }

    override fun onResume() {
        super.onResume()
        val uri = requireActivity().intent.data
        println(uri)
        if (viewModel.verifyOAuthResponse(uri, getString(R.string.manifest_scheme))) {
            val code = viewModel.getCode(uri!!)

            viewModel.getAccessToken(clientId, clientSecret, code!!, redirectUrl)
                .observe(viewLifecycleOwner, Observer {
                    println(it)
                    when (it.status) {
                        Result.Status.LOADING -> showLoadingDialog(R.string.getting_access_token)
                        Result.Status.SUCCESS -> {
                            cancelLoadingScreen()
                            nextActivity(MainActivity::class.java)
                        }
                        Result.Status.ERROR -> {
                            cancelLoadingScreen()
                            if (it.message != null) {
                                showToastMessage(it.message)
                            } else {
                                showToastMessage(R.string.warning_message_access_token)
                            }
                        }
                    }
                })
        }
    }
}