package com.example

class SupplyChainRepoJSON: SupplyChainRepo {
    override fun fetchCompanySupplyChain(companyId: Any): SupplyChain {

        if (companyId == "ZC789") {
            return SupplyChain(directSuppliers = listOf("ZS456"), indirectSuppliers = listOf(""))
        }
        TODO("Not yet implemented")
    }
}