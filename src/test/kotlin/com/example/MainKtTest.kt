package com.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MainKtTest {

//    Spec:
//    Given a user [U] is present in a top level customer organisation [O]
//    And a organisation [O] has a  list of direct suppliers [DirSup]
//    When [U] requests a list of direct suppliers
//    Then [DirSup] is returned

    @Test
    fun `When 'O' only has one direct supplier in 'DirSup', that supplier is returned to the 'U'`() {
//      Somehow create scenario that : when domain tries to look up company for ZU123 it gets back company ZC789
        val userRepoThatReturnsAFixedCompanyForAFixedUser = object : UserRepo {
            override fun fetchCompanyIdThatUserBelongsTo(userId: Any): String {
                if (userId == "ZU123") {
                    return "ZC789"
                }
                return "unknown user"
            }
        }

//      Somehow create scenario that : When domain tries to look up a list of suppliers for ZC789 it gets back a supply chain that includes direct suppliers (ZS456) (ie. listOf("ZS456"))
        val supplyChainRepoThatReturnsFixedListOfOneDirectSupplier = object : SupplyChainRepo {
            override fun fetchCompanySupplyChain(companyId: Any): SupplyChain {
                if (companyId == "ZC789") {
                    return SupplyChain(
                        directSuppliers = listOf("ZS456")
                    )
                }
                return SupplyChain(directSuppliers = listOf(""))
            }
        }

        val domain = Domain(
            userRepoThatReturnsAFixedCompanyForAFixedUser,
            supplyChainRepoThatReturnsFixedListOfOneDirectSupplier
        )
        val expected = listOf("ZS456")
        val actual = domain.getDirectSuppliersForUser("ZU123")

        assertEquals(expected, actual)
    }

    @Test
    fun `When 'O' has multiple direct suppliers in 'DirSup', they are all returned to the 'U'`() {

//      Somehow create scenario that : when domain tries to look up company for ZU122 it gets back ZC788
        val userRepoThatReturnsFixedUser = object : UserRepo {
            override fun fetchCompanyIdThatUserBelongsTo(userId: Any): String {
                if (userId == "ZU122") {
                    return "ZC788"
                }
                return ""
            }
        }

//      Somehow create scenario that : When domain tries to look up suppliers for ZC788 it gets back a supply chain that includes direct suppliers (ZS455, ZS456)
        val supplyChainRepoThatReturnsFixedListOfMultipleDirectSuppliers = object : SupplyChainRepo {
            override fun fetchCompanySupplyChain(companyId: Any): SupplyChain {
                if (companyId == "ZC788") {
                    return SupplyChain(
                            directSuppliers = listOf("ZS455", "ZS457")
                        )
                    }
            return SupplyChain(listOf(""))
            }
        }

        val domain = Domain(userRepoThatReturnsFixedUser, supplyChainRepoThatReturnsFixedListOfMultipleDirectSuppliers)
        val expected = listOf("ZS455", "ZS457")
        val actual = domain.getDirectSuppliersForUser("ZU122")

        assertEquals(expected, actual)
    }

}












