package com.example

fun main(args: Array<String>) {


    val domain = Domain( )
    val supplierIds: List<String> = domain.getDirectSuppliersForUser("ZU123")
    println("Supplier Ids: ${supplierIds}")
}