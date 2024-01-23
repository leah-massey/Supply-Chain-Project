package com.example
interface UserRepo {
    fun fetchCompanyIdThatUserBelongsTo(userId: Any): String
}