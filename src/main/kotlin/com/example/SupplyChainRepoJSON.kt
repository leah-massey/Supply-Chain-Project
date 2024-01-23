package com.example

class SupplyChainRepoJSON: SupplyChainRepo {
    override fun fetchCompanySupplyChain(companyId: Any): SupplyChain {

        if (companyId == "ZC789") {
            return SupplyChain(listOf("ZS456"))
        }
        TODO("Not yet implemented")
    }
}