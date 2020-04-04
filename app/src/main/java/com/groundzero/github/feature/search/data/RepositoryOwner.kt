package com.groundzero.github.feature.search.data

import java.io.Serializable

data class RepositoryOwner(
    val id: Long,
    val name: String? = null,
    val avatar: String? = null,
    val htmlUrl: String? = null,
    val type: String? = null,
    val isSiteAdmin: Boolean? = null
) : Serializable