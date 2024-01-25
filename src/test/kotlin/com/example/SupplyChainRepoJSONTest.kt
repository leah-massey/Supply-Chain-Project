package com.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SupplyChainRepoJSONTest {
    @Test
    fun `the supply chain belonging to 'O' is returned`() {
        val underTest = SupplyChainRepoJSON()
        val expected = SupplyChain(directSuppliers = listOf("ZS111"), indirectSuppliers = listOf("ZS222", "ZS333"))
        val actual = underTest.fetchCompanySupplyChain("ZC789")

        assertEquals(expected, actual)
    }
}