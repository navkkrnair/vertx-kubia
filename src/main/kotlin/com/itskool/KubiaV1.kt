package com.itskool

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import org.slf4j.LoggerFactory

class KubiaV1 : AbstractVerticle() {
    private val logger = LoggerFactory.getLogger(KubiaV1::class.java)

    override fun start(promise: Promise<Void>) {
        val env = System.getenv()
        val httpPort = env.getOrDefault("HTTP_PORT", "8080").toInt()

        val router = Router.router(vertx)
        router.get()
            .handler {
                handleRequest(it)
            }
        vertx.createHttpServer()
            .requestHandler(router)
            .listen(httpPort) {
                if (it.succeeded())
                    logger.info("Kubia listening in port $httpPort")
                else
                    logger.error("Error starting server: ${it.cause().message}")
            }
    }

    private fun handleRequest(context: RoutingContext) {
        context.response()
            .end("Hello World -- Service:V1")
    }
}

fun main() {
    Vertx.vertx().deployVerticle(KubiaV1())
}
