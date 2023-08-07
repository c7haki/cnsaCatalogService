package de.yehaw.cnsacatalogservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CnsaCatalogServiceApplication

fun main(args: Array<String>) {
    runApplication<CnsaCatalogServiceApplication>(*args)
}
