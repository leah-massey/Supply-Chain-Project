package com.example

interface SupplyChainRepo {
    fun fetchCompanySupplyChain(companyId: String): SupplyChain
}