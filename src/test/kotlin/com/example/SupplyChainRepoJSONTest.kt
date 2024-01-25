package com.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SupplyChainRepoJSONTest {
    @Test
    fun `when 'O' has a supply chain with suppliers, the supply chain belonging to 'O' is returned`() {
        val underTest = SupplyChainRepoJSON()
        val expected = SupplyChain(directSuppliers = listOf("ZS111"), indirectSuppliers = listOf("ZS222", "ZS333"))
        val actual = underTest.fetchCompanySupplyChain("ZC789")

        assertEquals(expected, actual)
    }
    @Test
    fun `when 'O' is not in the supplyChainRepo, an empty supply chain is returned`() {
        val underTest = SupplyChainRepoJSON()
        val expected = SupplyChain(directSuppliers = emptyList(), indirectSuppliers = emptyList())
        val actual = underTest.fetchCompanySupplyChain("ZC781")

        assertEquals(expected, actual)
    }
    @Test
    fun `when 'O' has no suppliers, an empty supply chain is returned`() {
        val underTest = SupplyChainRepoJSON()
        val expected = SupplyChain(directSuppliers = emptyList(), indirectSuppliers = emptyList())
        val actual = underTest.fetchCompanySupplyChain("ZC711")

        assertEquals(expected, actual)
    }
}