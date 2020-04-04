package com.groundzero.github.feature.owner.data

import com.groundzero.github.feature.owner.api.OwnerResponse

object OwnerDto {
    fun fromResponse(ownerResponse: OwnerResponse) = Owner(
        ownerResponse.id,
        ownerResponse.name,
        ownerResponse.avatar,
        ownerResponse.htmlUrl,
        ownerResponse.type,
        ownerResponse.isSiteAdmin,
        ownerResponse.company,
        ownerResponse.location,
        ownerResponse.email,
        ownerResponse.bio,
        ownerResponse.createdAt
    )
}