package de.yehaw.cnsacatalogservice.adapter.`in`.web

import de.yehaw.cnsacatalogservice.config.CnsaProperties
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController(
    private val cnsaProperties: CnsaProperties,
) {

    @GetMapping("/")
    fun index() = cnsaProperties.greeting

}
