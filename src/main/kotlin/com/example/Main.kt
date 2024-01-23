package com.example

fun main(args: Array<String>) {

// dependency injection so we can now control userRepo and supplyChainRepoJSON
    val userRepo: UserRepo = FileUserRepo()
    val supplyChainRepoJSON: SupplyChainRepo = SupplyChainRepoJSON()

    val domain = Domain(userRepo, supplyChainRepoJSON )
    val directSupplierIds: List<String> = domain.getDirectSuppliersForUser("ZU123")
    println("direct supplier Ids: ${directSupplierIds}")
}






