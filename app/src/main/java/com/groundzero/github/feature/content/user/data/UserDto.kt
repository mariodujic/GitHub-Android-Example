package com.groundzero.github.feature.content.user.data

import com.groundzero.github.feature.content.user.api.UserResponse

object UserDto {
    fun fromResponse(userResponse: UserResponse): User {
        println(userResponse)
        return User(
            id = userResponse.id,
            avatar = userResponse.avatar,
            login = userResponse.login,
            htmlUrl = userResponse.htmlUrl,
            name = userResponse.name,
            company = userResponse.company,
            location = userResponse.location,
            email = userResponse.email,
            bio = userResponse.bio,
            publicRepoCount = userResponse.publicRepoCount,
            followers = userResponse.followers,
            following = userResponse.following,
            createdAt = userResponse.createdAt,
            updatedAt = userResponse.updatedAt
        )
    }
}