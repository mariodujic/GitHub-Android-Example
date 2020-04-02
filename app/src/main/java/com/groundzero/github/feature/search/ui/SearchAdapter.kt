package com.groundzero.github.feature.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.groundzero.github.databinding.ItemSearchBinding
import com.groundzero.github.feature.search.data.Repo

class SearchAdapter : ListAdapter<Repo, SearchAdapter.SearchViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(ItemSearchBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

        val searchResponse: Repo? = getItem(position)
        holder.bind(searchResponse)
    }

    class SearchViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(searchResponse: Repo?) {
            binding.repo = searchResponse
        }
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repo>() {
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Repo, newItem: Repo
    ) = oldItem == newItem
}