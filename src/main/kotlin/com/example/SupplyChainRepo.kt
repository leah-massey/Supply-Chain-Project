package com.example

class SupplyChainRepo {
    fun fetchCompanySupplyChain(companyId: Any): SupplyChain {

        if (companyId == "ZC789") {
            return SupplyChain(listOf("ZS456"))
        }
        TODO("Not yet implemented")
    }
}