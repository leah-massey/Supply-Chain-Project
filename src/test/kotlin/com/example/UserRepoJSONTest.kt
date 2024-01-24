package com.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UserRepoJSONTest {



    @Test
    fun `the company to which a user belongs is returned as a string`() {

        val underTest = UserRepoJSON()
        val expected: String = "ZC321"
        val actual: String = underTest.fetchCompanyIdThatUserBelongsTo("ZU456")

        assertEquals(expected, actual)
    }

    @Test
    fun `an unrecognised user returns an error ?????`() {

        val underTest = UserRepoJSON()
        val expected: String = "ERROR?"
        val actual: String = underTest.fetchCompanyIdThatUserBelongsTo("ZU450")

        assertEquals(expected, actual)
    }
}