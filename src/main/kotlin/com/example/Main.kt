package com.example

import org.http4k.core.HttpHandler
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters
import org.http4k.server.SunHttp
import org.http4k.server.asServer

fun main(args: Array<String>) {

    val printingApp: HttpHandler = DebuggingFilters.PrintRequest().then(app)
    val server = printingApp.asServer(SunHttp(9000)).start()

    println("Server started on " + server.port())
}






