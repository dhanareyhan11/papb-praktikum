package com.tifd.projectcomposedd.data

import kotlinx.serialization.Serializable

@Serializable
data class GithubUser(
    val login: String,
    val avatar_Url: String,
    val name: String?,
    val followers: Int,
    val following: Int
)
