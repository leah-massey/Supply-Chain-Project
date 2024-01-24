package com.example
interface UserRepo {
    fun fetchCompanyIdThatUserBelongsTo(userId: String): String
}