package com.example

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes

val app: HttpHandler = routes(

    "/suppliers" bind GET to { req: Request ->
        val type: String? = req.query("type")
        val userId: String = req.header("User-Id")?: "" //todo handle errors if no user Id
        val supplierId: String? = req.query("supplierId")

        val userRepo: UserRepo = UserRepoJSON()
        val supplyChainRepo: SupplyChainRepo = SupplyChainRepoJSON()
        val supplierRepo: SupplierRepo = SupplierRepoJSON()
        val domain = Domain(userRepo, supplyChainRepo, supplierRepo )


        if ((type == "direct") && (supplierId !== null) ) {
            val requestedSupplier = domain.getDirectSupplierThatHasSpecifiedId("$supplierId", userId)
            Response(OK).body("$requestedSupplier")
        } else if (type == "direct") {
            val directSupplierIds: List<String> = domain.getDirectSuppliersForUser(userId)
            Response(OK).body("$directSupplierIds")
        } else {
            Response(OK).body("You are clearly not looking for direct suppliers")
        }
    }


)


