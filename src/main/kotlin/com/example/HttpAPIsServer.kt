package com.example

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer

val app: HttpHandler = routes(

    "/suppliers" bind GET to { req: Request ->
        val type: String? = req.query("type")
        val userId: String = req.header("User-Id")?: ""

        if (type == "direct") {
            val userRepo: UserRepo = UserRepoJSON()
            val supplyChainRepoJSON: SupplyChainRepo = SupplyChainRepoJSON()

            val domain = Domain(userRepo, supplyChainRepoJSON )
            val directSupplierIds: List<String> = domain.getDirectSuppliersForUser(userId)
            Response(OK).body("$directSupplierIds")
        } else {
            Response(OK).body("You are clearly not looking for direct suppliers")
        }
    }
)


