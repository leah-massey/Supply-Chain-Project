package com.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MainKtTest{

//    Spec:
//    Given a user [U] is present in a top level customer organisation [O]
//    And a organisation [O] has a  list of direct suppliers [DirSup]
//    When [U] requests a list of direct suppliers
//    Then [DirSup] is returned

    @Test
    fun `When 'O' only has one direct supplier in 'DirSup', that supplier is returned to the 'U'`() {
//      Somehow create scenario that : when domain tries to look up company for ZU123 it gets back company ZC789
        val userRepoThatReturnsAFixedCompanyForAFixedUser = object: UserRepo {
            override fun fetchCompanyIdThatUserBelongsTo(userId: Any): String {
                if (userId == "ZU123") {
                    return "ZC789"
                }
                return "unknown user"
            }
        }

//      Somehow create scenario that : When domain tries to look up a list of suppliers for ZC789 it gets back a supply chain that includes direct suppliers (ZS456) (ie. listOf("ZS456"))
        val supplyChainRepoThatReturnsFixedListOfSuppliers = object: SupplyChainRepo {
            override fun fetchCompanySupplyChain(companyId: Any): SupplyChain {
                if (companyId == "ZC789") {
                    return SupplyChain(
                        directSuppliers = listOf("ZS456")
                    )
                }
                return SupplyChain( directSuppliers = listOf(""))
            }
        }

        val domain = Domain(userRepoThatReturnsAFixedCompanyForAFixedUser, supplyChainRepoThatReturnsFixedListOfSuppliers)
        val expected = listOf("ZS456")
        val actual = domain.getDirectSuppliersForUser("ZU123")

        assertEquals(expected, actual)
    }
}