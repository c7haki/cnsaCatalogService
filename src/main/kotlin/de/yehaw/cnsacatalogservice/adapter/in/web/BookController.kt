package de.yehaw.cnsacatalogservice.adapter.`in`.web

import de.yehaw.cnsacatalogservice.application.domain.model.Book
import de.yehaw.cnsacatalogservice.application.domain.service.BookService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/books")
class BookController(
    private val bookService: BookService
) {

    @GetMapping
    fun get(): Iterable<BookDto> = bookService.getBooks().map { it.toBookDto() }

    @GetMapping("{isbn}")
    fun getByIsbn(@PathVariable isbn: String): BookDto = bookService.getBook(isbn).toBookDto()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun post(@Valid @RequestBody book: BookDto): BookDto = bookService.addBook(book.toBook()).toBookDto()

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable isbn: String): Unit = bookService.removeBook(isbn)

    @PutMapping("{isbn}")
    fun put(@PathVariable isbn: String, @Valid @RequestBody book: BookDto): BookDto =
        bookService.editBook(isbn, book.toBook()).toBookDto()

}

data class BookDto(
    @get:NotBlank(message = "The book ISBN must not be blank.") @get:Pattern(
        regexp = "^[0-9]{10}|[0-9]{13}$", message = "The book ISBN format is invalid."
    ) val isbn: String,
    @get:NotBlank(message = "The book title must not be blank.") val title: String,
    @get:NotBlank(message = "The book author must not be blank.") val author: String,
    @get:NotNull(message = "The book price must be defined.") @get:Positive(message = "The book price must be greater than zero.") val price: Double,
    @get:NotBlank(message = "The publisher must not be blank.") val publisher: String,
) {
    fun toBook() = Book(isbn, title, author, price, publisher)
}

fun Book.toBookDto() = BookDto(this.isbn, this.title, this.author, this.price, this.publisher)
