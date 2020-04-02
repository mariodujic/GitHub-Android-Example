package com.groundzero.github.base

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.groundzero.github.di.helper.Injectable
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class BaseFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    fun showToastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}