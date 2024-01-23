package com.example

interface SupplyChainRepo {
    fun fetchCompanySupplyChain(companyId: Any): SupplyChain
}