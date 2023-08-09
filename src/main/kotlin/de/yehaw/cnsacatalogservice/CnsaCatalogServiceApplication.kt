package de.yehaw.cnsacatalogservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class CnsaCatalogServiceApplication

fun main(args: Array<String>) {
    runApplication<CnsaCatalogServiceApplication>(*args)
}
