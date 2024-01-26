package com.example

class Domain(val userRepo: UserRepo, val supplyChainRepo: SupplyChainRepo) {

    fun getDirectSuppliersForUser(userId: String): List<String> {

        //todo: Handle Errors
        val companyId: String? = userRepo.fetchCompanyIdThatUserBelongsTo(userId)

        if (companyId == null) {
            println("the user is not valid")
            return emptyList()
        }

        //todo: Handle Errors
        val supplyChain: SupplyChain = supplyChainRepo.fetchCompanySupplyChain(companyId)

        val directSupplierIds: List<String> = findDirectSuppliersForCompany(supplyChain, companyId)

        return directSupplierIds
    }

    fun getDirectSupplierThatHasSpecifiedId(supplierId: String): Supplier {

        return Supplier("", "", listOf(""))
    }

    private fun findDirectSuppliersForCompany(supplyChain: SupplyChain, companyId: Any): List<String> {

        return supplyChain.directSuppliers
    }
}

data class SupplyChain(val directSuppliers: List<String>, val indirectSuppliers: List<String>) {
}

data class Supplier(val supplierId: String, val supplierName: String, val customers: List<String>){

}

