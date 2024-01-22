package com.example

class FileUserRepo: UserRepo {
    override fun fetchCompanyIdThatUserBelongsTo(userId: Any): String {
        if (userId == "ZU123") {
            return "ZC789"
        }

        TODO("Not yet implemented")
    }
}