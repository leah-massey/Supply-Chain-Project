package com.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DomainTest {
//        Spec:
//        Given a user [U] is present in a top level customer organisation [O]
//        And an organisation [O] has a  list of direct suppliers [DirSup]
//        When [U] requests a list of direct suppliers
//        Then [DirSup] is returned

    @Nested
    inner class GetDirectSuppliersForUserFunctionTesting {

        // when domain tries to look up company for ZU123 it always gets back company ZC789
        val userRepoThatReturnsAFixedCompanyForAFixedUser = object : UserRepo {
            override fun fetchCompanyIdThatUserBelongsTo(userId: String): String? {
                if (userId == "ZU123") {
                    return "ZC789"
                }
                return null
            }
        }

        val supplierRepoPlaceHolder = object : SupplierRepo {
            override fun fetchSupplierById(supplierId: String): Supplier {
                return Supplier(
                    supplierId = "ZS456",
                    supplierName = "Potato King",
                    customers = listOf("ZC123", "ZC321")
                )
            }
        }

        @Test
        fun `When 'O' only has one direct supplier in 'DirSup', that supplier is returned to the 'U'`() {

//      Somehow create scenario that : When domain tries to look up a list of suppliers for ZC789 it gets back a supply chain that includes direct suppliers (ZS456) (ie. listOf("ZS456"))
            val supplyChainRepoThatReturnsFixedListOfOneDirectSupplier = object : SupplyChainRepo {
                override fun fetchCompanySupplyChain(companyId: String): SupplyChain {
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

            val supplierRepoThatReturnsFixedSupplier = object : SupplierRepo {
                override fun fetchSupplierById(supplierId: String): Supplier {
                    if (supplierId == "ZS456") {
                        return Supplier(
                            supplierId = "ZS456",
                            supplierName = "Potato King",
                            customers = listOf("ZC123", "ZC321")
                        )
                    }
                   return Supplier(
                       supplierId = "",
                       supplierName = "",
                       customers = listOf("")
                   )
                }
            }

            val domain = Domain(
                userRepoThatReturnsAFixedCompanyForAFixedUser,
                supplyChainRepoThatReturnsFixedListOfOneDirectSupplier,
                supplierRepoThatReturnsFixedSupplier
            )
            val expected = listOf("ZS456")
            val actual = domain.getDirectSuppliersForUser("ZU123")

            assertEquals(expected, actual)
        }

        @Test
        fun `When 'O' has multiple direct suppliers in 'DirSup', they are all returned to the 'U'`() {

//      Somehow create scenario that : When domain tries to look up suppliers for ZC788 it gets back a supply chain that includes direct suppliers (ZS455, ZS456)
            val supplyChainRepoThatReturnsFixedListOfMultipleDirectSuppliers = object : SupplyChainRepo {
                override fun fetchCompanySupplyChain(companyId: String): SupplyChain {
                    if (companyId == "ZC789") {
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

            val domain = Domain(userRepoThatReturnsAFixedCompanyForAFixedUser, supplyChainRepoThatReturnsFixedListOfMultipleDirectSuppliers, supplierRepoPlaceHolder)
            val expected = listOf("ZS455", "ZS457")
            val actual = domain.getDirectSuppliersForUser("ZU123")

            assertEquals(expected, actual)
        }

        @Test
        fun `When 'O' has both direct and indirect suppliers, only direct suppliers are returned to the 'U'`() {

//      Somehow create scenario that : When domain tries to look up suppliers for ZC788 it gets back a supply chain that includes direct suppliers (ZS455, ZS456) and indirect suppliers (ZS457)
            val supplyChainRepoThatReturnsAFixedCombinationOfDirectAndIndirectSuppliers = object: SupplyChainRepo {
                override fun fetchCompanySupplyChain(companyId: String): SupplyChain {
                    if (companyId == "ZC789") {
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

            val domain = Domain(userRepoThatReturnsAFixedCompanyForAFixedUser, supplyChainRepoThatReturnsAFixedCombinationOfDirectAndIndirectSuppliers, supplierRepoPlaceHolder)
            val expected = listOf("ZS455", "ZS456")
            val actual = domain.getDirectSuppliersForUser("ZU123")

            assertEquals(expected, actual)

        }

        @Test
        fun `When 'O' has no direct suppliers, an empty list is returned to 'U`() {

//      Somehow create scenario that : When domain tries to look up suppliers for ZC789 it gets back a supply chain that includes no direct suppliers
            val supplyChainRepoThatReturnsAFixedResponseThatHasNoDirectSuppliers = object: SupplyChainRepo {
                override fun fetchCompanySupplyChain(companyId: String): SupplyChain {
                    if (companyId == "ZC788") {
                        return SupplyChain(
                            directSuppliers = emptyList(),
                            indirectSuppliers = listOf("ZS222")
                        )
                    }
                    return SupplyChain(
                        directSuppliers = listOf(""),
                        indirectSuppliers = listOf("")
                    )
                }
            }

            val domain = Domain(userRepoThatReturnsAFixedCompanyForAFixedUser, supplyChainRepoThatReturnsAFixedResponseThatHasNoDirectSuppliers, supplierRepoPlaceHolder)
            val expected: List<String> = emptyList()
            val actual = domain.getDirectSuppliersForUser("ZU122")

            assertEquals(expected, actual)
        }

        @Test
        fun `When 'U' is not a recognised user, an empty list is returned`() {

            val supplyChainRepoMock = object: SupplyChainRepo {
                override fun fetchCompanySupplyChain(companyId: String): SupplyChain {
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

            val domain = Domain(userRepoThatReturnsAFixedCompanyForAFixedUser, supplyChainRepoMock, supplierRepoPlaceHolder)
            val expected: List<String> = emptyList()
            val actual = domain.getDirectSuppliersForUser("Z")
            println(actual.size)

            assertEquals(expected, actual)
        }

    }

    @Nested
    inner class GetDirectSupplierThatHasSpecifiedIdFunctionTesting{

//      when domain tries to look up company for ZU123 it gets back company ZC789
        val userRepoThatReturnsAFixedCompanyForAFixedUser = object : UserRepo {
            override fun fetchCompanyIdThatUserBelongsTo(userId: String): String? {
                if (userId == "ZU123") {
                    return "ZC789"
                }
            return null
            }
        }

        @Test
        fun `a 'D' present in 'O', whose ID also matches the specified ID is returned`() {

//          When domain tries to look up a list of suppliers for ZC789 it gets back a supply chain that includes [D] (ZS456) -> [ZS456, ZS111]
            val supplyChainRepoThatReturnsAFixedListOfDirectSuppliers = object : SupplyChainRepo {
                override fun fetchCompanySupplyChain(companyId: String): SupplyChain {
                    if (companyId == "ZC789") {
                        return SupplyChain(
                            directSuppliers = listOf("ZS455", "ZS456"),
                            indirectSuppliers = listOf("ZS457")
                        )
                    }
                    return SupplyChain(
                        directSuppliers = emptyList(),
                        indirectSuppliers = emptyList()
                    )
                }
            }

            val supplierRepoThatReturnsFixedSupplier = object: SupplierRepo {
                override fun fetchSupplierById(supplierId: String): Supplier {
                    if (supplierId == "ZS456" ) {
                        return Supplier(
                            supplierId = "ZS456",
                            supplierName = "Potato King",
                            customers = listOf("ZC123", "ZC321")
                        )
                    }
                return Supplier(
                    supplierId = "",
                    supplierName = "",
                    customers = emptyList()
                )
                }
            }

            val domain = Domain(userRepoThatReturnsAFixedCompanyForAFixedUser, supplyChainRepoThatReturnsAFixedListOfDirectSuppliers, supplierRepoThatReturnsFixedSupplier)
            val expected = Supplier(
                supplierId = "ZS456",
                supplierName = "Potato King",
                customers = listOf("ZC123", "ZC321")
            )
            val actual = domain.getDirectSupplierThatHasSpecifiedId("ZS456", "ZU123")

            assertEquals(expected, actual)
        }

        @Test
        fun `if specified 'D' ID not present in 'O', an empty 'D' is returned`() {

//          When domain tries to look up a list of suppliers for ZC789 it gets back a supply chain that includes [D] (ZS456) -> [ZS456, ZS111]
            val supplyChainRepoThatReturnsAFixedListOfDirectSuppliers = object : SupplyChainRepo {
                override fun fetchCompanySupplyChain(companyId: String): SupplyChain {
                    if (companyId == "ZC789") {
                        return SupplyChain(
                            directSuppliers = listOf("ZS455", "ZS458"),
                            indirectSuppliers = listOf("ZS457")
                        )
                    }
                    return SupplyChain(
                        directSuppliers = emptyList(),
                        indirectSuppliers = emptyList()
                    )
                }
            }

            val supplierRepoThatReturnsFixedSupplier = object: SupplierRepo {
                override fun fetchSupplierById(supplierId: String): Supplier {
                    if (supplierId == "ZS456" ) {
                        return Supplier(
                            supplierId = "",
                            supplierName = "",
                            customers = emptyList()
                        )
                    }
                    return Supplier(
                        supplierId = "ZS456",
                        supplierName = "Potato King",
                        customers = listOf("ZC123", "ZC321")
                    )
                }
            }

            val domain = Domain(userRepoThatReturnsAFixedCompanyForAFixedUser, supplyChainRepoThatReturnsAFixedListOfDirectSuppliers, supplierRepoThatReturnsFixedSupplier)
            val expected = Supplier(
                supplierId = "",
                supplierName = "",
                customers = emptyList()
            )
            val actual = domain.getDirectSupplierThatHasSpecifiedId("ZS456", "ZU123")

            assertEquals(expected, actual)
        }

        @Test
        fun `if no 'D' in 'O' has matching specified ID, but specified ID matches an indirect supplier in 'O', an empty 'D' is still returned`() {
            val supplyChainRepoThatReturnsAFixedListOfDirectSuppliers = object : SupplyChainRepo {
                override fun fetchCompanySupplyChain(companyId: String): SupplyChain {
                    if (companyId == "ZC789") {
                        return SupplyChain(
                            directSuppliers = listOf("ZS455"),
                            indirectSuppliers = listOf("ZS457",  "ZS456")
                        )
                    }
                    return SupplyChain(
                        directSuppliers = emptyList(),
                        indirectSuppliers = emptyList()
                    )
                }
            }

            val supplierRepoThatReturnsFixedSupplier = object: SupplierRepo {
                override fun fetchSupplierById(supplierId: String): Supplier {
                    if (supplierId == "ZS456" ) {
                        return Supplier(
                            supplierId = "ZS456",
                            supplierName = "Potato King",
                            customers = listOf("ZC123", "ZC321")
                        )
                    }
                    return Supplier(
                        supplierId = "",
                        supplierName = "",
                        customers = emptyList()
                    )
                }
            }

            val domain = Domain(userRepoThatReturnsAFixedCompanyForAFixedUser, supplyChainRepoThatReturnsAFixedListOfDirectSuppliers, supplierRepoThatReturnsFixedSupplier)
            val expected = Supplier(
                supplierId = "",
                supplierName = "",
                customers = emptyList()
            )
            val actual = domain.getDirectSupplierThatHasSpecifiedId("ZS456", "ZU123")

            assertEquals(expected, actual)

        }
    }


}