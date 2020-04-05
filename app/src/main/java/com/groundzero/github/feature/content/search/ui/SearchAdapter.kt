package com.groundzero.github.feature.content.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.groundzero.github.databinding.ItemSearchBinding
import com.groundzero.github.feature.content.search.data.Repository

class SearchAdapter(private val listener: SearchListener) :
    PagedListAdapter<Repository, SearchAdapter.SearchViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

        val searchResponse: Repository? = getItem(position)
        if (searchResponse != null) {
            holder.bind(searchResponse)
        }
    }

    class SearchViewHolder(
        private val binding: ItemSearchBinding,
        private val listener: SearchListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: Repository) {
            binding.repo = repository
            itemView.setOnClickListener { listener.onSearchRepositoryClick(repository) }
            binding.itemSearchThumbnail.setOnClickListener { listener.onSearchOwnerClick(repository.owner!!) }
            binding.itemSearchOwnerParent.setOnClickListener{ listener.onSearchOwnerClick(repository.owner!!)}
        }
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repository>() {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Repository, newItem: Repository
    ) = oldItem == newItem
}