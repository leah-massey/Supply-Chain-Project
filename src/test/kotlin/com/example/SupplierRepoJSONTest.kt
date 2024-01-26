package com.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SupplierRepoJSONTest {
   @Test
   fun `when present in the supplier repo, the supplier object with corresponding Id is returned`() {

       val underTest = SupplierRepoJSON()
       val expected: Supplier = Supplier(
           supplierId = "ZS456",
           supplierName = "Sam's Apples",
           customers = listOf("ZC123", "ZS013")
       )

       val actual: Supplier = underTest.fetchSupplierById("ZS456")
       assertEquals(expected, actual)

   }

    @Test
    fun `when specified supplier is not in the supplier repo, an empty supplier object is returned`() {

        val underTest = SupplierRepoJSON()
        val expected: Supplier = Supplier(
            supplierId = "",
            supplierName = "",
            customers = emptyList()
        )

        val actual: Supplier = underTest.fetchSupplierById("ZS450")
        assertEquals(expected, actual)

    }

}