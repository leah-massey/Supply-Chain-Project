package com.example
import java.io.File
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.Nulls
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

data class User(@JsonProperty("id") val id: String, @JsonProperty("companyId") val companyId: String)


class UserRepoJSON: UserRepo {
    override fun fetchCompanyIdThatUserBelongsTo(userId: String): String? {
        val mapper = jacksonObjectMapper()
        val users: List<User> = mapper.readValue(File("./src/resources/UserRepo.json"))

        val user = users.firstOrNull{
            it.id == userId
        }
        return user?.companyId
        }
}