package com.example

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

class `SupplierRepoJSON`: SupplierRepo {
    override fun fetchSupplierById(supplierId: String): Supplier {

        val mapper = jacksonObjectMapper()
        val entireSupplierRepo: List<Supplier> = mapper.readValue(File("./src/resources/SupplierRepo.json"))

        val supplierWithSpecifiedId: Supplier? = entireSupplierRepo.firstOrNull{supplier ->
            supplier.supplierId == supplierId
        }

        if (supplierWithSpecifiedId == null) {
            return Supplier(
                supplierId = "",
                supplierName = "",
                customers = emptyList()
            )
        }

        return supplierWithSpecifiedId

    }
}
