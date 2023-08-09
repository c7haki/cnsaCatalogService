package de.yehaw.cnsacatalogservice.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "cnsa")
class CnsaProperties {
    lateinit var greeting: String
}
