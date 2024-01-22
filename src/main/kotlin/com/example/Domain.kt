package com.example

class Domain(val userRepo: UserRepo, val supplyChainRepo: SupplyChainRepo) {

    fun getDirectSuppliersForUser(userId: String): List<String> {

        //todo: Handle Errors
        val companyId: String = userRepo.fetchCompanyIdThatUserBelongsTo(userId)
        println("company Id: ${companyId}")


        //todo: Handle Errors
        val supplyChain: SupplyChain = supplyChainRepo.fetchCompanySupplyChain(companyId)

        val directSupplierIds: List<String> = findDirectSuppliersForCompany(supplyChain, companyId)
        println("directSupplierIds: ${directSupplierIds}")
        return directSupplierIds
    }

    private fun findDirectSuppliersForCompany(supplyChain: SupplyChain, companyId: Any): List<String> {
        return supplyChain.directSuppliers
    }
}

class SupplyChain(val directSuppliers: List<String>) {
}
