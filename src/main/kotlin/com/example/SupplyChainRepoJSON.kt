package com.example

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

data class SupplyChainTypesJson(@JsonProperty("directSuppliers") val directSuppliers: List<String>,  @JsonProperty("indirectSuppliers") val indirectSuppliers: List<String>)
data class SupplyChainJson(@JsonProperty("companyId") val companyId: String, @JsonProperty("suppliers") val suppliers: SupplyChainTypesJson)
class SupplyChainRepoJSON: SupplyChainRepo {
    override fun fetchCompanySupplyChain(companyId: String): SupplyChain {

        val mapper = jacksonObjectMapper()
        val entireSupplyChainRepo: List<SupplyChainJson> = mapper.readValue(File("./src/resources/SupplyChain.json"))

        val companySupplyChain = entireSupplyChainRepo.firstOrNull {supplier ->
            supplier.companyId == companyId
        }

        // if company does not have a supply chain return empty lists
        if (companySupplyChain == null) {
            return SupplyChain(emptyList(), emptyList())
        }

        val companySuppliersJson: SupplyChainTypesJson = companySupplyChain.suppliers
        val companySuppliers: SupplyChain = SupplyChain(companySuppliersJson.directSuppliers, companySuppliersJson.indirectSuppliers)

        return companySuppliers

    }
}