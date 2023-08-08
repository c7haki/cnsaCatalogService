package de.yehaw.cnsacatalogservice.adapter.`in`.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @GetMapping("/")
    fun index() = "Welcome to the book catalog service!"

}
