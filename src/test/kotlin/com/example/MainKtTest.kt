package com.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MainKtTest {
//        Spec:
//        Given a user [U] is present in a top level customer organisation [O]
//        And an organisation [O] has a  list of direct suppliers [DirSup]
//        When [U] requests a list of direct suppliers
//        Then [DirSup] is returned

    @Nested
    inner class DomainTesting {

        //      Somehow create scenario that : when domain tries to look up company for ZU123 it gets back company ZC789
        val userRepoThatReturnsAFixedCompanyForAFixedUser = object : UserRepo {
            override fun fetchCompanyIdThatUserBelongsTo(userId: Any): String {
                if (userId == "ZU123") {
                    return "ZC789"
                }
                return "unknown user"
            }
        }


        @Test
        fun `When 'O' only has one direct supplier in 'DirSup', that supplier is returned to the 'U'`() {

//      Somehow create scenario that : When domain tries to look up a list of suppliers for ZC789 it gets back a supply chain that includes direct suppliers (ZS456) (ie. listOf("ZS456"))
            val supplyChainRepoThatReturnsFixedListOfOneDirectSupplier = object : SupplyChainRepo {
                override fun fetchCompanySupplyChain(companyId: Any): SupplyChain {
                    if (companyId == "ZC789") {
                        return SupplyChain(
                            directSuppliers = listOf("ZS456"),
                            indirectSuppliers = listOf("")
                        )
                    }
                    return SupplyChain(
                        directSuppliers = listOf(""),
                        indirectSuppliers = listOf(""))
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

//      Somehow create scenario that : When domain tries to look up suppliers for ZC788 it gets back a supply chain that includes direct suppliers (ZS455, ZS456)
            val supplyChainRepoThatReturnsFixedListOfMultipleDirectSuppliers = object : SupplyChainRepo {
                override fun fetchCompanySupplyChain(companyId: Any): SupplyChain {
                    if (companyId == "ZC788") {
                        return SupplyChain(
                            directSuppliers = listOf("ZS455", "ZS457"),
                            indirectSuppliers = listOf("")
                        )
                    }
                    return SupplyChain(
                        directSuppliers = listOf(""),
                        indirectSuppliers = listOf("")
                    )
                }
            }

            val domain = Domain(userRepoThatReturnsAFixedCompanyForAFixedUser, supplyChainRepoThatReturnsFixedListOfMultipleDirectSuppliers)
            val expected = listOf("ZS455", "ZS457")
            val actual = domain.getDirectSuppliersForUser("ZU122")

            assertEquals(expected, actual)
        }

        @Test
        fun `When 'O' has both direct and indirect suppliers, only direct suppliers are returned to the 'U'`() {

//      Somehow create scenario that : When domain tries to look up suppliers for ZC788 it gets back a supply chain that includes direct suppliers (ZS455, ZS456) and indirect suppliers (ZS457)
            val supplyChainRepoThatReturnsAFixedCombinationOfDirectAndIndirectSuppliers = object: SupplyChainRepo {
                override fun fetchCompanySupplyChain(companyId: Any): SupplyChain {
                    if (companyId == "ZC788") {
                        return SupplyChain(
                            directSuppliers = listOf("ZS455", "ZS456"),
                            indirectSuppliers = listOf("ZS457")
                        )
                    }
                    return SupplyChain(
                        directSuppliers = listOf(""),
                        indirectSuppliers = listOf("")
                    )
                }
            }

            val domain = Domain(userRepoThatReturnsAFixedCompanyForAFixedUser, supplyChainRepoThatReturnsAFixedCombinationOfDirectAndIndirectSuppliers)
            val expected = listOf("ZS455", "ZS456")
            val actual = domain.getDirectSuppliersForUser("ZU122")

            assertEquals(expected, actual)

        }

        @Test
        fun `When 'O' has no direct suppliers, an empty list is returned to 'U`() {

//      Somehow create scenario that : When domain tries to look up suppliers for ZC789 it gets back a supply chain that includes no direct suppliers
            val supplyChainRepoThatReturnsAFixedResponseThatHasNoDirectSuppliers = object: SupplyChainRepo {
                override fun fetchCompanySupplyChain(companyId: Any): SupplyChain {
                    if (companyId == "ZC788") {
                        return SupplyChain(
                            directSuppliers = listOf(""),
                            indirectSuppliers = listOf("ZS222")
                        )
                    }
                    return SupplyChain(
                        directSuppliers = listOf(""),
                        indirectSuppliers = listOf("")
                    )
                }
            }

            val domain = Domain(userRepoThatReturnsAFixedCompanyForAFixedUser, supplyChainRepoThatReturnsAFixedResponseThatHasNoDirectSuppliers)
            val expected = listOf("")
            val actual = domain.getDirectSuppliersForUser("ZU122")

            assertEquals(expected, actual)
        }
    }


}












