package com.example
import java.io.File
import org.json.JSONArray
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

data class User(@JsonProperty("id") val id: String, @JsonProperty("companyId") val companyId: String)


class UserRepoJSON: UserRepo {
    override fun fetchCompanyIdThatUserBelongsTo(userId: Any): String {
        val mapper = jacksonObjectMapper()
        val users: List<User> = mapper.readValue(File("./src/resources/UserRepo.json"))

        for (user in users) {
            if (user.id == userId) {
                return user.companyId
            }
        }
return "there is no user match on our system"
    }
}