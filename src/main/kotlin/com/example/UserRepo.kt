package com.example

class UserRepo {
    fun fetchCompanyIdThatUserBelongsTo(userId: Any): String {
        if (userId == "ZU123") {
            return "ZC789"
        }

        TODO("Not yet implemented")
    }
}