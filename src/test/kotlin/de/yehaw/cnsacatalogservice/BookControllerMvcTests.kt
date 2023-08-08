package de.yehaw.cnsacatalogservice

import de.yehaw.cnsacatalogservice.adapter.`in`.web.BookController
import de.yehaw.cnsacatalogservice.application.domain.service.BookNotFoundException
import de.yehaw.cnsacatalogservice.application.domain.service.BookService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(BookController::class)
class BookControllerMvcTests(
    @Autowired private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var bookService: BookService

    @Test
    fun whenGetBookNotExistingThenShouldReturn404() {
        val isbn = "9876543210"
        given(bookService.getBook(isbn)).willThrow(BookNotFoundException::class.java)
        mockMvc.perform(get("/api/v1/books/$isbn")).andExpect(status().isNotFound)
    }

}
