package com.example

class Domain {
    fun getDirectSuppliersForUser(userID: String): List<String> {
        val userRepo = UserRepo()
        //todo: Handle Errors
        val companyID: String = userRepo.fetchCompanyIdThatUserBelongsTo(userId)

        val supplyChainRepo = SupplyChainRepo()
        //todo: Handle Errors
        val supplyChain: SupplyChain = supplyChainRepo.fetchCompanySupplyChain(companyId)

        val directSupplierIds: List<String> = findDirectSuppliersForCompany(supplyChain, companyID)

        return directSupplierIds
    }
}