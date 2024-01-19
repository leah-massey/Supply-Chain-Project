package com.example

class Domain {
    fun getDirectSuppliersForUser(userId: String): List<String> {
        val userRepo = UserRepo()
        //todo: Handle Errors
        val companyId: String = userRepo.fetchCompanyIdThatUserBelongsTo(userId)

        val supplyChainRepo = SupplyChainRepo()
        //todo: Handle Errors
        val supplyChain: SupplyChain = supplyChainRepo.fetchCompanySupplyChain(companyId)

        val directSupplierIds: List<String> = findDirectSuppliersForCompany(supplyChain, companyId)

        return directSupplierIds
    }

    private fun findDirectSuppliersForCompany(supplyChain: SupplyChain, companyID: Any): List<String> {
        TODO("Not yet implemented")
    }
}

class SupplyChain {

}
