package com.test.miprimeraapp.data.remote

data class PostResquest(
    val userId: Long,
    val id: Long?,
    val title: String?,
    val body: String?
)