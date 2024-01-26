package com.example

interface SupplierRepo {
    fun fetchSupplierById(supplierId: String): Supplier
}
