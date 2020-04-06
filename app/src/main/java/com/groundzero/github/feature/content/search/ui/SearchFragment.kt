package com.groundzero.github.feature.content.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.groundzero.github.R
import com.groundzero.github.base.BaseFragment
import com.groundzero.github.data.Result
import com.groundzero.github.databinding.FragmentSearchBinding
import com.groundzero.github.di.helper.injectViewModel
import com.groundzero.github.feature.authentication.common.AuthenticationActivity
import com.groundzero.github.feature.content.owner.data.Owner
import com.groundzero.github.feature.content.search.data.Repository
import com.groundzero.github.feature.content.user.data.User
import com.groundzero.github.utils.toggleSideView
import com.groundzero.github.view.RecyclerItemDecorator

class SearchFragment : BaseFragment(), SearchListener {

    private val searchAdapter = SearchAdapter(this)
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentSearchBinding.inflate(inflater, container, false).apply {
        searchRepositoryRecyclerView.apply {
            adapter = searchAdapter
            addItemDecoration(
                RecyclerItemDecorator(
                    resources.getDimension(R.dimen.minimal_padding).toInt()
                )
            )
        }
        viewModel = injectViewModel(viewModelFactory)
        viewModel.setInitialQuery("Android")
        observeSearchQuery()
        observeUser(this)
        implementListeners(this)
        recyclerViewListener(this)
        setSideToggleButton(this)
    }.root

    private fun observeSearchQuery() {
        viewModel.repositoryLive.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Result.Status.LOADING -> {
                    showLoadingDialog(R.string.loading_dialog_search_repository)
                }
                Result.Status.SUCCESS -> {
                    cancelLoadingScreen()
                    if (it.data != null) {
                        searchAdapter.submitList(it.data)
                    }
                    viewModel.isLoadingOnScroll = false
                }
                Result.Status.ERROR -> {
                    cancelLoadingScreen()
                    if (it.message != null) {
                        showToastMessage(it.message)
                    } else {
                        showToastMessage(R.string.warning_message_search_repository)
                    }
                    viewModel.isLoadingOnScroll = false
                }
            }
        })
    }

    private fun observeUser(binding: FragmentSearchBinding) {
        viewModel.getUserLive().observe(viewLifecycleOwner, Observer { userResult ->
            when (userResult.status) {
                Result.Status.LOADING -> binding.searchToolbarParent.visibility = View.GONE
                Result.Status.SUCCESS -> {
                    if (userResult.data != null) {
                        binding.user = userResult.data
                        binding.searchToolbarParent.visibility = View.VISIBLE
                        binding.searchRepositoryUserProfile.setOnClickListener {
                            onUserProfileClick(userResult.data)
                        }
                        binding.searchRepositoryUserAvatar.setOnClickListener {
                            onUserProfileClick(userResult.data)
                        }
                    }
                }
                Result.Status.ERROR -> {
                    binding.searchToolbarParent.visibility = View.GONE
                }
            }
        })
    }

    private fun implementListeners(binding: FragmentSearchBinding) {
        binding.searchQueryParent.setStartIconOnClickListener {
            binding.searchQuery.text.toString().also {
                if (it != "") {
                    viewModel.searchRepositories(it)
                } else {
                    showToastMessage(R.string.query_empty_warning)
                }
                clearSearchInputFocus(binding)
            }
        }
        binding.searchLogout.setOnClickListener { onLogoutClick() }
    }

    private fun recyclerViewListener(binding: FragmentSearchBinding) {
        binding.searchRepositoryRecyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    if (!viewModel.isLoadingOnScroll) {
                        viewModel.nextPage()
                        viewModel.isLoadingOnScroll = true
                    }
                }
            }
        })
    }

    private fun clearSearchInputFocus(binding: FragmentSearchBinding) {
        if (binding.searchQueryParent.hasFocus()) {
            binding.searchQueryParent.clearFocus()
        }
    }

    private fun setSideToggleButton(binding: FragmentSearchBinding) {
        binding.apply {
            searchSortParent.setOnLongClickListener {
                searchSortParent.toggleSideView(!viewModel.isToggleButtonShown).start()
                viewModel.isToggleButtonShown = !viewModel.isToggleButtonShown
                true
            }

            searchSortParent.setOnClickListener {
                viewModel.nextSort()
            }

            viewModel.getSortTypeLive().observe(viewLifecycleOwner, Observer { sort ->
                searchSortType.text = sort.getType()
            })
        }
    }

    private fun onLogoutClick() {
        viewModel.deleteUserData()
        nextActivity(AuthenticationActivity::class.java)
    }

    private fun onUserProfileClick(user: User) {
        val action = SearchFragmentDirections.actionSearchFragmentToUserFragment(user)
        findNavController().navigate(action)
    }

    override fun onSearchRepositoryClick(repository: Repository) {
        val action = SearchFragmentDirections.actionSearchFragmentToRepositoryFragment(repository)
        findNavController().navigate(action)
    }

    override fun onSearchOwnerClick(owner: Owner) {
        val action = SearchFragmentDirections.actionSearchFragmentToOwnerFragment(owner)
        findNavController().navigate(action)
    }
}