package com.example

fun main(args: Array<String>) {

// dependency injection so we can now control these things
    val userRepo: UserRepo = FileUserRepo()
    val supplyChainRepo = SupplyChainRepo()

    val domain = Domain(userRepo, supplyChainRepo )
    val supplierIds: List<String> = domain.getDirectSuppliersForUser("ZU123")
    println("Supplier Ids: ${supplierIds}")
}

interface UserRepo {
    fun fetchCompanyIdThatUserBelongsTo(userId: Any): String
}


